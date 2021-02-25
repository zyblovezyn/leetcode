/*
 * Copyright (c) 2015, 2020, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


package org.graalvm.compiler.replacements;

import static jdk.vm.ci.services.Services.IS_IN_NATIVE_IMAGE;
import static org.graalvm.compiler.core.common.GraalOptions.UseEncodedGraphs;
import static org.graalvm.compiler.nodes.graphbuilderconf.IntrinsicContext.CompilationContext.INLINE_AFTER_PARSING;

import jdk.internal.vm.compiler.collections.EconomicMap;
import org.graalvm.compiler.bytecode.BytecodeProvider;
import org.graalvm.compiler.debug.DebugCloseable;
import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.debug.TimerKey;
import org.graalvm.compiler.graph.SourceLanguagePositionProvider;
import org.graalvm.compiler.java.GraphBuilderPhase;
import org.graalvm.compiler.loop.phases.ConvertDeoptimizeToGuardPhase;
import org.graalvm.compiler.nodes.EncodedGraph;
import org.graalvm.compiler.nodes.GraphEncoder;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.StructuredGraph.AllowAssumptions;
import org.graalvm.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration;
import org.graalvm.compiler.nodes.graphbuilderconf.InlineInvokePlugin;
import org.graalvm.compiler.nodes.graphbuilderconf.IntrinsicContext;
import org.graalvm.compiler.nodes.graphbuilderconf.InvocationPlugins;
import org.graalvm.compiler.nodes.graphbuilderconf.LoopExplosionPlugin;
import org.graalvm.compiler.nodes.graphbuilderconf.MethodSubstitutionPlugin;
import org.graalvm.compiler.nodes.graphbuilderconf.NodePlugin;
import org.graalvm.compiler.nodes.graphbuilderconf.ParameterPlugin;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.phases.BasePhase;
import org.graalvm.compiler.phases.OptimisticOptimizations;
import org.graalvm.compiler.phases.common.CanonicalizerPhase;
import org.graalvm.compiler.phases.util.Providers;

import jdk.vm.ci.code.Architecture;
import jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * A graph decoder that provides all necessary encoded graphs on-the-fly (by parsing the methods and
 * encoding the graphs).
 */
public class CachingPEGraphDecoder extends PEGraphDecoder {

    protected final Providers providers;
    protected final GraphBuilderConfiguration graphBuilderConfig;
    protected final OptimisticOptimizations optimisticOpts;
    private final AllowAssumptions allowAssumptions;
    private final EconomicMap<ResolvedJavaMethod, EncodedGraph> graphCache;
    private final BasePhase<? super CoreProviders> postParsingPhase;
    private static final TimerKey buildGraphTime = DebugContext.timer("TruffleBuildGraphTime");

    public CachingPEGraphDecoder(Architecture architecture, StructuredGraph graph, Providers providers, GraphBuilderConfiguration graphBuilderConfig, OptimisticOptimizations optimisticOpts,
                    AllowAssumptions allowAssumptions, LoopExplosionPlugin loopExplosionPlugin, InvocationPlugins invocationPlugins, InlineInvokePlugin[] inlineInvokePlugins,
                    ParameterPlugin parameterPlugin,
                    NodePlugin[] nodePlugins, ResolvedJavaMethod peRootForInlining, SourceLanguagePositionProvider sourceLanguagePositionProvider,
                    BasePhase<? super CoreProviders> postParsingPhase, EconomicMap<ResolvedJavaMethod, EncodedGraph> graphCache) {
        super(architecture, graph, providers, loopExplosionPlugin,
                        invocationPlugins, inlineInvokePlugins, parameterPlugin, nodePlugins, peRootForInlining, sourceLanguagePositionProvider);

        this.providers = providers;
        this.graphBuilderConfig = graphBuilderConfig;
        this.optimisticOpts = optimisticOpts;
        this.allowAssumptions = allowAssumptions;
        this.graphCache = graphCache;
        this.postParsingPhase = postParsingPhase;
    }

    protected GraphBuilderPhase.Instance createGraphBuilderPhaseInstance(IntrinsicContext initialIntrinsicContext) {
        return new GraphBuilderPhase.Instance(providers, graphBuilderConfig, optimisticOpts, initialIntrinsicContext);
    }

    @SuppressWarnings("try")
    private EncodedGraph createGraph(ResolvedJavaMethod method, MethodSubstitutionPlugin plugin, BytecodeProvider intrinsicBytecodeProvider, boolean isSubstitution) {
        StructuredGraph graphToEncode;
        if (isSubstitution && (UseEncodedGraphs.getValue(options) || IS_IN_NATIVE_IMAGE)) {
            // These must go through Replacements to find the graph to use.
            graphToEncode = providers.getReplacements().getMethodSubstitution(plugin, method, INLINE_AFTER_PARSING, allowAssumptions,
                            null, options);
        } else {
            graphToEncode = buildGraph(method, plugin, intrinsicBytecodeProvider, isSubstitution);
        }

        /*
         * ConvertDeoptimizeToGuardPhase reduces the number of merges in the graph, so that fewer
         * frame states will be created. This significantly reduces the number of nodes in the
         * initial graph.
         */
        try (DebugContext.Scope scope = debug.scope("createGraph", graphToEncode)) {
            new ConvertDeoptimizeToGuardPhase().apply(graphToEncode, providers);
        } catch (Throwable t) {
            throw debug.handle(t);
        }

        EncodedGraph encodedGraph = GraphEncoder.encodeSingleGraph(graphToEncode, architecture);
        graphCache.put(method, encodedGraph);
        return encodedGraph;
    }

    @SuppressWarnings("try")
    private StructuredGraph buildGraph(ResolvedJavaMethod method, MethodSubstitutionPlugin plugin, BytecodeProvider intrinsicBytecodeProvider, boolean isSubstitution) {
        StructuredGraph graphToEncode;// @formatter:off
        graphToEncode = new StructuredGraph.Builder(options, debug, allowAssumptions).
                useProfilingInfo(false).
                trackNodeSourcePosition(graphBuilderConfig.trackNodeSourcePosition()).
                method(plugin != null ? plugin.getSubstitute(providers.getMetaAccess()) : method).
                setIsSubstitution(isSubstitution).
                cancellable(graph.getCancellable()).
                build();
        // @formatter:on
        try (DebugContext.Scope scope = debug.scope("buildGraph", graphToEncode); DebugCloseable a = buildGraphTime.start(debug)) {
            IntrinsicContext initialIntrinsicContext = intrinsicBytecodeProvider != null
                            ? new IntrinsicContext(method, plugin.getSubstitute(providers.getMetaAccess()), intrinsicBytecodeProvider, INLINE_AFTER_PARSING)
                            : null;
            GraphBuilderPhase.Instance graphBuilderPhaseInstance = createGraphBuilderPhaseInstance(initialIntrinsicContext);
            graphBuilderPhaseInstance.apply(graphToEncode);
            CanonicalizerPhase.create().apply(graphToEncode, providers);
            if (postParsingPhase != null) {
                postParsingPhase.apply(graphToEncode, providers);
            }
        } catch (Throwable ex) {
            throw debug.handle(ex);
        }
        return graphToEncode;
    }

    @Override
    protected EncodedGraph lookupEncodedGraph(ResolvedJavaMethod method, MethodSubstitutionPlugin plugin, BytecodeProvider intrinsicBytecodeProvider, boolean isSubstitution,
                    boolean trackNodeSourcePosition) {
        EncodedGraph result = graphCache.get(method);
        if (result == null && method.hasBytecodes()) {
            result = createGraph(method, plugin, intrinsicBytecodeProvider, isSubstitution);
        }
        return result;
    }
}

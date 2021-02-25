/*
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core.test.backend;

import org.graalvm.compiler.core.GraalCompiler;
import org.graalvm.compiler.core.gen.LIRCompilerBackend;
import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.lir.gen.LIRGenerationResult;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.phases.OptimisticOptimizations;

import jdk.vm.ci.code.Architecture;

public abstract class BackendTest extends GraalCompilerTest {

    public BackendTest() {
        super();
    }

    public BackendTest(Class<? extends Architecture> arch) {
        super(arch);
    }

    @SuppressWarnings("try")
    protected LIRGenerationResult getLIRGenerationResult(final StructuredGraph graph, OptimisticOptimizations optimizations) {
        DebugContext debug = graph.getDebug();
        try (DebugContext.Scope s = debug.scope("FrontEnd")) {
            GraalCompiler.emitFrontEnd(getProviders(), getBackend(), graph, getDefaultGraphBuilderSuite(), optimizations, graph.getProfilingInfo(), createSuites(graph.getOptions()));
        } catch (Throwable e) {
            throw debug.handle(e);
        }

        LIRGenerationResult lirGen = LIRCompilerBackend.emitLIR(getBackend(), graph, null, null, createLIRSuites(graph.getOptions()));
        return lirGen;
    }

    protected LIRGenerationResult getLIRGenerationResult(final StructuredGraph graph) {
        return getLIRGenerationResult(graph, OptimisticOptimizations.NONE);
    }
}

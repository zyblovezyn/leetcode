/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.loop.phases;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import org.graalvm.compiler.core.common.GraalOptions;
import org.graalvm.compiler.debug.CounterKey;
import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.loop.LoopEx;
import org.graalvm.compiler.loop.LoopPolicies;
import org.graalvm.compiler.loop.LoopsData;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.options.Option;
import org.graalvm.compiler.options.OptionKey;
import org.graalvm.compiler.options.OptionType;
import org.graalvm.compiler.phases.common.CanonicalizerPhase;

public class LoopFullUnrollPhase extends LoopPhase<LoopPolicies> {
    public static class Options {
        @Option(help = "", type = OptionType.Expert) public static final OptionKey<Integer> FullUnrollMaxApplication = new OptionKey<>(60);
    }

    private static final CounterKey FULLY_UNROLLED_LOOPS = DebugContext.counter("FullUnrolls");
    public static final Comparator<LoopEx> LOOP_COMPARATOR;
    static {
        ToDoubleFunction<LoopEx> loopFreq = e -> e.loop().getHeader().getFirstPredecessor().getRelativeFrequency();
        ToIntFunction<LoopEx> loopDepth = e -> e.loop().getDepth();
        LOOP_COMPARATOR = Comparator.comparingDouble(loopFreq).thenComparingInt(loopDepth).reversed();
    }

    private final CanonicalizerPhase canonicalizer;

    public LoopFullUnrollPhase(CanonicalizerPhase canonicalizer, LoopPolicies policies) {
        super(policies);
        this.canonicalizer = canonicalizer;
    }

    @Override
    protected void run(StructuredGraph graph, CoreProviders context) {
        if (GraalOptions.FullUnroll.getValue(graph.getOptions())) {
            DebugContext debug = graph.getDebug();
            if (graph.hasLoops()) {
                boolean peeled;
                int applications = 0;
                do {
                    peeled = false;
                    final LoopsData dataCounted = new LoopsData(graph);
                    dataCounted.detectedCountedLoops();
                    List<LoopEx> countedLoops = dataCounted.countedLoops();
                    countedLoops.sort(LOOP_COMPARATOR);
                    for (LoopEx loop : countedLoops) {
                        if (getPolicies().shouldFullUnroll(loop)) {
                            debug.log("FullUnroll %s", loop);
                            LoopTransformations.fullUnroll(loop, context, canonicalizer);
                            FULLY_UNROLLED_LOOPS.increment(debug);
                            debug.dump(DebugContext.DETAILED_LEVEL, graph, "FullUnroll %s", loop);
                            peeled = true;
                            break;
                        }
                    }
                    dataCounted.deleteUnusedNodes();
                    applications++;
                } while (peeled && applications < Options.FullUnrollMaxApplication.getValue(graph.getOptions()));
            }
        }
    }

    @Override
    public boolean checkContract() {
        return false;
    }
}

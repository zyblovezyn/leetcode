/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.virtual.phases.ea;

import static org.graalvm.compiler.core.common.GraalOptions.EscapeAnalyzeOnly;

import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.StructuredGraph.ScheduleResult;
import org.graalvm.compiler.nodes.cfg.ControlFlowGraph;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.phases.common.CanonicalizerPhase;
import org.graalvm.compiler.phases.graph.ReentrantBlockIterator;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * This phase performs read and (simple) write elimination on a graph. It operates on multiple
 * granularities, i.e., before and after high-tier lowering. The phase iterates the graph in a
 * reverse-post-order fashion {@linkplain ReentrantBlockIterator} and tracks the currently active
 * value for a specific {@linkplain LocationIdentity}, which allows the removal of subsequent reads
 * if no writes happen in between, etc. if the value read from memory is in a virtual register
 * (node).
 *
 * A trivial example for read elimination can be seen below:
 *
 * <pre>
 * int i = object.fieldValue;
 * // code not changing object.fieldValue but using i
 * consume(object.fieldValue);
 * </pre>
 *
 * Read elimination will transform this piece of code to the code below and remove the second,
 * unnecessary, memory read of the field:
 *
 * <pre>
 * int i = object.fieldValue;
 * // code not changing object.fieldValue but using i
 * consume(i);
 * </pre>
 */
public class EarlyReadEliminationPhase extends EffectsPhase<CoreProviders> {

    protected final boolean considerGuards;

    public EarlyReadEliminationPhase(CanonicalizerPhase canonicalizer) {
        super(1, canonicalizer, true);
        this.considerGuards = true;
    }

    public EarlyReadEliminationPhase(CanonicalizerPhase canonicalizer, boolean considerGuards) {
        super(1, canonicalizer, true);
        this.considerGuards = considerGuards;
    }

    @Override
    protected void run(StructuredGraph graph, CoreProviders context) {
        if (VirtualUtil.matches(graph, EscapeAnalyzeOnly.getValue(graph.getOptions()))) {
            runAnalysis(graph, context);
        }
    }

    @Override
    protected Closure<?> createEffectsClosure(CoreProviders context, ScheduleResult schedule, ControlFlowGraph cfg) {
        assert schedule == null;
        return new ReadEliminationClosure(cfg, considerGuards);
    }

    @Override
    public float codeSizeIncrease() {
        return 2f;
    }
}

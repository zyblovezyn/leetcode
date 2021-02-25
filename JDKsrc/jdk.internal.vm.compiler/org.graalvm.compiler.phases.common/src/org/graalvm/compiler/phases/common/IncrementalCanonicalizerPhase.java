/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.phases.common;

import org.graalvm.compiler.graph.Graph.NodeEventScope;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.phases.BasePhase;
import org.graalvm.compiler.phases.PhaseSuite;
import org.graalvm.compiler.phases.common.util.EconomicSetNodeEventListener;

/**
 * A phase suite that applies {@linkplain CanonicalizerPhase canonicalization} to a graph after all
 * phases in the suite have been applied if any of the phases changed the graph.
 */
public class IncrementalCanonicalizerPhase<C extends CoreProviders> extends PhaseSuite<C> {

    private final CanonicalizerPhase canonicalizer;

    public IncrementalCanonicalizerPhase(CanonicalizerPhase canonicalizer) {
        this.canonicalizer = canonicalizer;
    }

    public IncrementalCanonicalizerPhase(CanonicalizerPhase canonicalizer, BasePhase<? super C> phase) {
        this.canonicalizer = canonicalizer;
        appendPhase(phase);
    }

    @Override
    @SuppressWarnings("try")
    protected void run(StructuredGraph graph, C context) {
        EconomicSetNodeEventListener listener = new EconomicSetNodeEventListener();
        try (NodeEventScope nes = graph.trackNodeEvents(listener)) {
            super.run(graph, context);
        }

        if (!listener.getNodes().isEmpty()) {
            canonicalizer.applyIncremental(graph, context, listener.getNodes(), null, false);
        }
    }
}

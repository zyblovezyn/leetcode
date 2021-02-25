/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.debug.DebugCloseable;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.gc.BarrierSet;
import org.graalvm.compiler.nodes.memory.FixedAccessNode;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.phases.BasePhase;

public class WriteBarrierAdditionPhase extends BasePhase<CoreProviders> {
    @SuppressWarnings("try")
    @Override
    protected void run(StructuredGraph graph, CoreProviders context) {
        BarrierSet barrierSet = context.getPlatformConfigurationProvider().getBarrierSet();
        for (FixedAccessNode n : graph.getNodes().filter(FixedAccessNode.class)) {
            try (DebugCloseable scope = n.graph().withNodeSourcePosition(n)) {
                barrierSet.addBarriers(n);
            }
        }
    }

    @Override
    public boolean checkContract() {
        return false;
    }
}

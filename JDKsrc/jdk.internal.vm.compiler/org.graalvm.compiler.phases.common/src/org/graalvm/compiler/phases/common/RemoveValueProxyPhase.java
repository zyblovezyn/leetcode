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


package org.graalvm.compiler.phases.common;

import org.graalvm.compiler.nodes.FrameState;
import org.graalvm.compiler.nodes.LoopExitNode;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.StructuredGraph.FrameStateVerification;
import org.graalvm.compiler.nodes.util.GraphUtil;
import org.graalvm.compiler.phases.Phase;

public class RemoveValueProxyPhase extends Phase {

    @Override
    protected void run(StructuredGraph graph) {
        for (LoopExitNode exit : graph.getNodes(LoopExitNode.TYPE)) {
            exit.removeProxies();
            FrameState frameState = exit.stateAfter();
            if (frameState != null && frameState.isExceptionHandlingBCI()) {
                // The parser will create loop exits with such BCIs on the exception handling path.
                // Loop optimizations must avoid duplicating such exits
                // We clean them up here otherwise they could survive until code generation
                exit.setStateAfter(null);
                GraphUtil.tryKillUnused(frameState);
            }
        }
        graph.setHasValueProxies(false);
        graph.weakenFrameStateVerification(FrameStateVerification.ALL_EXCEPT_LOOP_EXIT);
    }
}

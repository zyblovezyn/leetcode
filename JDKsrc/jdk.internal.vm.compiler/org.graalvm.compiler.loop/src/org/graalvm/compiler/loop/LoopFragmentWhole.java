/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.loop;

import org.graalvm.compiler.core.common.cfg.Loop;
import org.graalvm.compiler.graph.Graph;
import org.graalvm.compiler.graph.Graph.DuplicationReplacement;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.graph.NodeBitMap;
import org.graalvm.compiler.nodes.EndNode;
import org.graalvm.compiler.nodes.FixedNode;
import org.graalvm.compiler.nodes.LoopBeginNode;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.cfg.Block;

public class LoopFragmentWhole extends LoopFragment {

    public LoopFragmentWhole(LoopEx loop) {
        super(loop);
    }

    public LoopFragmentWhole(LoopFragmentWhole original) {
        super(null, original);
    }

    @Override
    public LoopFragmentWhole duplicate() {
        LoopFragmentWhole loopFragmentWhole = new LoopFragmentWhole(this);
        loopFragmentWhole.reify();
        return loopFragmentWhole;
    }

    private void reify() {
        assert this.isDuplicate();

        patchNodes(null);

        mergeEarlyExits();
    }

    @Override
    public NodeBitMap nodes() {
        if (nodes == null) {
            Loop<Block> loop = loop().loop();
            NodeBitMap loopNodes = graph().createNodeBitMap();
            LoopFragment.computeNodes(loopNodes, graph(), loop(), LoopFragment.toHirBlocks(loop.getBlocks()), LoopFragment.toHirBlocks(loop.getLoopExits()));
            nodes = loopNodes;
        }
        return nodes;
    }

    @Override
    protected ValueNode prim(ValueNode b) {
        return getDuplicatedNode(b);
    }

    @Override
    protected DuplicationReplacement getDuplicationReplacement() {
        final FixedNode entry = loop().entryPoint();
        final Graph graph = this.graph();
        return new DuplicationReplacement() {

            private EndNode endNode;

            @Override
            public Node replacement(Node o) {
                if (o == entry) {
                    if (endNode == null) {
                        endNode = graph.add(new EndNode());
                    }
                    return endNode;
                }
                return o;
            }
        };
    }

    public FixedNode entryPoint() {
        if (isDuplicate()) {
            LoopBeginNode newLoopBegin = getDuplicatedNode(original().loop().loopBegin());
            return newLoopBegin.forwardEnd();
        }
        return loop().entryPoint();
    }

    @Override
    protected void beforeDuplication() {
        // nothing to do
    }

    @Override
    public void insertBefore(LoopEx loop) {
        // nothing to do
    }
}

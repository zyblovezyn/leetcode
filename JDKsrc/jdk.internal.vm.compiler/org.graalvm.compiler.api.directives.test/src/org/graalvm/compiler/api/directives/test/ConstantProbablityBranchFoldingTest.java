/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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



package org.graalvm.compiler.api.directives.test;

import org.graalvm.compiler.api.directives.GraalDirectives;
import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.graph.iterators.NodeIterable;
import org.graalvm.compiler.nodes.IfNode;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.junit.Assert;
import org.junit.Test;

public class ConstantProbablityBranchFoldingTest extends GraalCompilerTest {

    public static int branchFoldingSnippet1() {
        if (GraalDirectives.injectBranchProbability(0.5, true)) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int branchFoldingSnippet2() {
        if (GraalDirectives.injectBranchProbability(0.5, false)) {
            return 1;
        } else {
            return 2;
        }
    }

    @Test
    public void testEarlyFolding1() {
        test("branchFoldingSnippet1");
    }

    @Test
    public void testEarlyFolding2() {
        test("branchFoldingSnippet2");
    }

    @Override
    protected void checkLowTierGraph(StructuredGraph graph) {
        NodeIterable<IfNode> ifNodes = graph.getNodes(IfNode.TYPE);
        Assert.assertEquals("IfNode count", 0, ifNodes.count());
    }
}

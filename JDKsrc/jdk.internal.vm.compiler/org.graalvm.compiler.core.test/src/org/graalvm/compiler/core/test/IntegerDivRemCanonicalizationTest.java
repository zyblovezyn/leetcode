/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core.test;

import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.calc.SignedRemNode;
import org.junit.Test;

public class IntegerDivRemCanonicalizationTest extends GraalCompilerTest {

    public static int redundantRemNode(int a, int b) {
        int r = (a - a % b) / b;
        return r;
    }

    @Test
    public void testRedundantRemNode() {
        StructuredGraph graph = parseForCompile(getResolvedJavaMethod("redundantRemNode"));
        createCanonicalizerPhase().apply(graph, getProviders());
        // We expect the remainder to be canonicalized away.
        assertTrue(graph.getNodes().filter(SignedRemNode.class).count() == 0);
    }
}

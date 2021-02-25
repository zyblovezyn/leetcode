/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2020, Arm Limited and affiliates. All rights reserved.
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
import org.graalvm.compiler.nodes.calc.NegateNode;
import org.graalvm.compiler.nodes.calc.RightShiftNode;
import org.graalvm.compiler.nodes.calc.UnsignedRightShiftNode;
import org.junit.Test;

public class NegateCanonicalizationTest extends GraalCompilerTest {

    public static int negateInt(int x) {
        return -(x >> 31);
    }

    public static long negateLong(long x) {
        return -(x >> 63);
    }

    public static int signExtractInt(int x) {
        return (x >> 31) >>> 31;
    }

    public static long signExtractLong(long x) {
        return (x >> 63) >>> 63;
    }

    private void checkNodes(String methodName) {
        StructuredGraph graph = parseForCompile(getResolvedJavaMethod(methodName));
        createCanonicalizerPhase().apply(graph, getProviders());
        assertTrue(graph.getNodes().filter(NegateNode.class).count() == 0);
        assertTrue(graph.getNodes().filter(RightShiftNode.class).count() == 0);
        assertTrue(graph.getNodes().filter(UnsignedRightShiftNode.class).count() == 1);
    }

    @Test
    public void testNegate() {
        checkNodes("negateInt");
        checkNodes("negateLong");
        checkNodes("signExtractInt");
        checkNodes("signExtractLong");
    }
}

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


package org.graalvm.compiler.core.test;

import org.graalvm.compiler.api.directives.GraalDirectives;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.calc.RightShiftNode;
import org.graalvm.compiler.nodes.calc.UnsignedRightShiftNode;
import org.junit.Test;

public class IntegerDivPowerOf2Test extends GraalCompilerTest {

    public static int positiveDivByPowerOf2(boolean flag) {
        int val = flag ? 1 : 10;
        GraalDirectives.blackhole(val);
        return val / 8;
    }

    @Test
    public void testPositiveDivByPowerOf2() {
        StructuredGraph graph = parseForCompile(getResolvedJavaMethod("positiveDivByPowerOf2"));
        // We expect no rounding is needed
        assertTrue(countShiftNode(graph) == 1);
    }

    private static int countShiftNode(StructuredGraph graph) {
        return graph.getNodes().filter(node -> node instanceof RightShiftNode || node instanceof UnsignedRightShiftNode).count();
    }

    public static int unknownDivByPowerOf2(boolean flag) {
        int val = flag ? 0x800000F0 : 0x20;
        GraalDirectives.blackhole(val);
        return val / 8;
    }

    @Test
    public void testUnknownDivByPowerOf2() {
        StructuredGraph graph = parseForCompile(getResolvedJavaMethod("unknownDivByPowerOf2"));
        // We expect no rounding is needed
        assertTrue(graph.getNodes().filter(RightShiftNode.class).count() <= 1);
    }

}

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


package org.graalvm.compiler.nodes.test;

import static org.graalvm.compiler.nodes.type.StampTool.stampForTrailingZeros;
import static org.graalvm.compiler.test.GraalTest.assertTrue;

import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.test.GraphTest;
import org.junit.Test;

public class StampToolTest extends GraphTest {

    @Test
    public void testStampForTrailingZeros() {
        assertIntegerStampEquals(stampForTrailingZeros(forInt(0)), 32);
        assertIntegerStampEquals(stampForTrailingZeros(forInt(1)), 0);
        assertIntegerStampEquals(stampForTrailingZeros(forInt(-1)), 0);
        assertIntegerStampEquals(stampForTrailingZeros(forInt(Integer.MIN_VALUE)), 31);
        assertIntegerStampEquals(stampForTrailingZeros(forInt(Integer.MAX_VALUE)), 0);
    }

    private static IntegerStamp forInt(int value) {
        return StampFactory.forInteger(32, value, value);
    }

    private static void assertIntegerStampEquals(Stamp stamp, int value) {
        assertTrue(stamp instanceof IntegerStamp);
        IntegerStamp iStamp = (IntegerStamp) stamp;
        assertTrue(iStamp.lowerBound() == value);
        assertTrue(iStamp.upperBound() == value);
    }

}

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

import org.junit.Test;

public class IntegerStampShiftTest extends GraalCompilerTest {

    public static int unsignedShiftPositiveInt(boolean f) {
        int h = f ? 0x7FFFFFF0 : 0x7FFFFF00;
        return h >>> 8;
    }

    @Test
    public void testUnsignedShiftPositiveInt() {
        test("unsignedShiftPositiveInt", false);
    }

    public static int unsignedShiftNegativeInt(boolean f) {
        int h = f ? 0xFFFFFFF0 : 0xFFFFFF00;
        return h >>> 8;
    }

    @Test
    public void testUnsignedShiftNegativeInt() {
        test("unsignedShiftNegativeInt", false);
    }

    public static long unsignedShiftPositiveLong(boolean f) {
        long h = f ? 0x7FFFFFFFFFFFFFF0L : 0x7FFFFFFFFFFFFF00L;
        return h >>> 8;
    }

    @Test
    public void testUnsignedShiftPositiveLong() {
        test("unsignedShiftPositiveLong", false);
    }

    public static long unsignedShiftNegativeLong(boolean f) {
        long h = f ? 0xFFFFFFFFFFFFFFF0L : 0xFFFFFFFFFFFFFF00L;
        return h >>> 8;
    }

    @Test
    public void testUnsignedShiftNegativeLong() {
        test("unsignedShiftNegativeLong", false);
    }
}

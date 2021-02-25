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


package org.graalvm.compiler.hotspot.amd64.test;

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.junit.Test;

public class NumberOfTrailingZeroings003 extends GraalCompilerTest {

    public static boolean numberOfTrailingZeros0() {
        return Integer.numberOfTrailingZeros(0) == 32;
    }

    @Test
    public void testNumberOfTrailingZeros0() {
        test("numberOfTrailingZeros0");
    }

    public static boolean numberOfTrailingZeros1() {
        return Integer.numberOfTrailingZeros(1) == 0;
    }

    @Test
    public void testNumberOfTrailingZeros1() {
        test("numberOfTrailingZeros1");
    }

    public static boolean numberOfTrailingZerosM1() {
        return Integer.numberOfTrailingZeros(-1) == 0;
    }

    @Test
    public void testNumberOfTrailingZerosM1() {
        test("numberOfTrailingZerosM1");
    }

    public static boolean numberOfTrailingZerosMin() {
        return Integer.numberOfTrailingZeros(Integer.MIN_VALUE) == 31;
    }

    @Test
    public void testNumberOfTrailingZerosMin() {
        test("numberOfTrailingZerosMin");
    }

    public static boolean numberOfTrailingZerosMax() {
        return Integer.numberOfTrailingZeros(Integer.MAX_VALUE) == 0;
    }

    @Test
    public void testNumberOfTrailingZerosMax() {
        test("numberOfTrailingZerosMax");
    }
}

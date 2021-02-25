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


package org.graalvm.compiler.hotspot.test;

import static org.graalvm.compiler.serviceprovider.JavaVersionUtil.JAVA_SPEC;

import org.graalvm.compiler.api.directives.GraalDirectives;
import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

public class BoxDeoptimizationTest extends GraalCompilerTest {

    private static void checkJDK() {
        Assume.assumeTrue(JAVA_SPEC == 8 || JAVA_SPEC >= 13);
    }

    public static void testIntegerSnippet() {
        Object[] values = {42, -42, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Integer.valueOf(42));
        Assert.assertSame(values[1], Integer.valueOf(-42));
    }

    @Test
    public void testInteger() {
        checkJDK();
        test("testIntegerSnippet");
    }

    public static void testLongSnippet() {
        long highBitsOnly = 2L << 40;
        Object[] values = {42L, -42L, highBitsOnly, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Long.valueOf(42));
        Assert.assertSame(values[1], Long.valueOf(-42));
        Assert.assertNotSame(values[2], highBitsOnly);
    }

    @Test
    public void testLong() {
        checkJDK();
        test("testLongSnippet");
    }

    public static void testCharSnippet() {
        Object[] values = {'a', 'Z', new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Character.valueOf('a'));
        Assert.assertSame(values[1], Character.valueOf('Z'));
    }

    @Test
    public void testChar() {
        checkJDK();
        test("testCharSnippet");
    }

    public static void testShortSnippet() {
        Object[] values = {(short) 42, (short) -42, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Short.valueOf((short) 42));
        Assert.assertSame(values[1], Short.valueOf((short) -42));
    }

    @Test
    public void testShort() {
        checkJDK();
        test("testShortSnippet");
    }

    public static void testByteSnippet() {
        Object[] values = {(byte) 42, (byte) -42, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Byte.valueOf((byte) 42));
        Assert.assertSame(values[1], Byte.valueOf((byte) -42));
    }

    @Test
    public void testByte() {
        checkJDK();
        test("testByteSnippet");
    }

    public static void testBooleanSnippet() {
        Object[] values = {true, false, new Exception()};
        GraalDirectives.deoptimize();
        Assert.assertSame(values[0], Boolean.valueOf(true));
        Assert.assertSame(values[1], Boolean.valueOf(false));
    }

    @Test
    public void testBoolean() {
        checkJDK();
        test("testBooleanSnippet");
    }
}

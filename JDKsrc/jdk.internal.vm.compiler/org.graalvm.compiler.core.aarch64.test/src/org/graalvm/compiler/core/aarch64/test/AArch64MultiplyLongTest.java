/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Arm Limited. All rights reserved.
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


package org.graalvm.compiler.core.aarch64.test;

import org.junit.Test;

public class AArch64MultiplyLongTest extends AArch64MatchRuleTest {

    public long signedMulLong(int a, int b) {
        return a * (long) b;
    }

    public long signedMulLongFromShort(short a, short b) {
        return (long) a * b;
    }

    public long signedMulLongFromChar(char a, char b) {
        return a * (long) b;
    }

    public long signedMulLongFromByte(byte a, byte b) {
        return (long) a * b;
    }

    @Test
    public void testSignedMulLong() {
        test("signedMulLong", 0x12345678, 0x87654321);
        checkLIR("signedMulLong", op -> op.name().equals("SMULL"), 1);
        test("signedMulLongFromShort", (short) 32767, (short) -32768);
        checkLIR("signedMulLongFromShort", op -> op.name().equals("SMULL"), 1);
        test("signedMulLongFromChar", (char) 59999, (char) 65535);
        checkLIR("signedMulLongFromChar", op -> op.name().equals("SMULL"), 1);
        test("signedMulLongFromByte", (byte) 10, (byte) -256);
        checkLIR("signedMulLongFromByte", op -> op.name().equals("SMULL"), 1);
    }

    public long signedMNegLong1(int a, int b) {
        return -(a * (long) b);
    }

    public long signedMNegLong2(int a, int b) {
        return a * (-(long) b);
    }

    @Test
    public void testSignedMNegLong() {
        test("signedMNegLong1", 0x89abcdef, 0xfedcba98);
        checkLIR("signedMNegLong1", op -> op.name().equals("SMNEGL"), 1);
        test("signedMNegLong2", 0x89abcdef, 0xfedcba98);
        checkLIR("signedMNegLong2", op -> op.name().equals("SMNEGL"), 1);
    }

    public long signedMAddLong1(int a, int b, long c) {
        return c + a * (long) b;
    }

    public long signedMAddLong2(int a, int b, long c) {
        return a * (long) b + c;
    }

    @Test
    public void testSignedMAddLong() {
        test("signedMAddLong1", 0x22228888, 0xaaaacccc, 0x123456789abcdL);
        checkLIR("signedMAddLong1", op -> op.name().equals("SMADDL"), 1);
        test("signedMAddLong2", 0x22228888, 0xaaaacccc, 0x123456789abcdL);
        checkLIR("signedMAddLong2", op -> op.name().equals("SMADDL"), 1);
    }

    public long signedMSubLong(int a, int b, long c) {
        return c - a * (long) b;
    }

    @Test
    public void testSignedMSubLong() {
        test("signedMSubLong", 0x99995555, 0xeeeebbbb, 0x3456789abcdefL);
        checkLIR("signedMSubLong", op -> op.name().equals("SMSUBL"), 1);
    }
}

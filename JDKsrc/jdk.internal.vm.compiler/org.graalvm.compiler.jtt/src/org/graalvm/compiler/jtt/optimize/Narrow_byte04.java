/*
 * Copyright (c) 2018, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.jtt.optimize;

import org.graalvm.compiler.jtt.JTTTest;
import org.junit.Test;

/*
 */
public class Narrow_byte04 extends JTTTest {
    static final byte NEG_FLAG = (byte) -80;

    public static byte test(byte b) {
        byte hBits = (byte) (b & 0xF0);
        byte loBits = (byte) (b & 0x0F);
        return hBits == NEG_FLAG ? loBits : b;
    }

    @Test
    public void run0() throws Throwable {
        runTest("test", ((byte) 0xb1));
    }

    @Test
    public void run1() throws Throwable {
        runTest("test", ((byte) 0xFF));
    }

    @Test
    public void run3() throws Throwable {
        runTest("test", ((byte) 0));
    }
}

/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.jtt.hotspot;

//@formatter:off

/**
 * Array overflow not handled correctly with loop optimzations.
 *
 * @test
 * @bug 7005594
 * @run shell Test7005594.sh
 */
public class Test7005594 {

    private static int test0(byte[] a) {
        int result = 0;
        for (int i = 0; i < a.length; i += ((0x7fffffff >> 1) + 1)) {
            result += a[i];
        }
        return result;
    }

    public static int test() {
        byte[] a = new byte[(0x7fffffff >> 1) + 2];
        try {
            test0(a);
        } catch (ArrayIndexOutOfBoundsException e) {
            return 95;
        }
        return 97;
    }

}

/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Arm Limited and affiliates. All rights reserved.
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

import jdk.vm.ci.aarch64.AArch64;
import jdk.vm.ci.runtime.JVMCI;
import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assume.assumeTrue;

public class AArch64PairLoadStoreTest extends GraalCompilerTest {

    @Before
    public void checkAArch64() {
        assumeTrue("skipping AArch64 specific test", JVMCI.getRuntime().getHostJVMCIBackend().getTarget().arch instanceof AArch64);
    }

    public static long parameterSpill(long v1, long v2, long v3, long v4, long v5, long v6, long v7, long v8, long v9, long v10) {
        long value0 = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
        long value1 = v9 + v10;
        return value0 + value1;
    }

    @Test
    public void testParameterSpill() {
        test("parameterSpill", 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
    }

    static class A {
        static String a = "adsaf";

        static String b = "asfgsfd";

        static int c;

        static int d;
    }

    public static int pairLoadStaticFields() {
        if (A.a == null || A.a != A.b) {
            return A.b.length();
        }
        return A.a.length();
    }

    @Test
    public void testPairLoadStaticFields() {
        test("pairLoadStaticFields");
    }

    public static int pairStoreStaticFields(int m, int n) {
        A.c = m;
        A.d = n;
        return A.c + A.d;
    }

    @Test
    public void testPairStoreStaticFields() {
        test("pairStoreStaticFields", 1, 2);
    }
}

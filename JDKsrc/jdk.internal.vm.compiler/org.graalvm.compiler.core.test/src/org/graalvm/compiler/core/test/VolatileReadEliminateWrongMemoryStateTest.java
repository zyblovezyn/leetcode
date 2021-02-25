/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2020, Red Hat Inc. All rights reserved.
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

// See https://bugs.openjdk.java.net/browse/JDK-8247832
public class VolatileReadEliminateWrongMemoryStateTest extends GraalCompilerTest {

    private static volatile int volatileField;
    private static int field;

    @SuppressWarnings("unused")
    public static int testMethod() {
        field = 0;
        int v = volatileField;
        field += 1;
        v = volatileField;
        field += 1;
        return field;
    }

    @Test
    public void test1() {
        test("testMethod");
    }

    public static void testMethod2(Object obj) {
        synchronized (obj) {
            volatileField++;
        }
    }

    @Test
    public void test2() {
        test("testMethod2", new Object());
    }
}

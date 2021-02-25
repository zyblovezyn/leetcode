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
/*
 */


package org.graalvm.compiler.jtt.threads;

import org.graalvm.compiler.jtt.JTTTest;
import org.junit.Test;

public final class SynchronizedParserInlineTest extends JTTTest {

    private static SynchronizedParserInlineTest object = new SynchronizedParserInlineTest();

    public static Integer test(boolean b) {
        foo(object);
        return b ? 42 : 1337;
    }

    @BytecodeParserForceInline
    public static synchronized void foo(SynchronizedParserInlineTest o) {
        o.notifyAll();
    }

    @Test
    public void run0() {
        runTest("test", false);
    }

    public static Integer test1(int b) {
        return foo1(b);
    }

    @BytecodeParserForceInline
    public static synchronized int foo1(int b) {
        if (b < 0) {
            return 7777;
        } else if (b > 100) {
            throw new RuntimeException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void run1() {
        runTest("test1", -1);
        runTest("test1", 1);
        runTest("test1", 101);
    }

    public static Integer test2(int b) {
        return foo2(b);
    }

    @BytecodeParserForceInline
    public static int foo2(int b) {
        if (b < 0) {
            return 7777;
        } else if (b > 100) {
            throw new RuntimeException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void run2() {
        runTest("test2", -1);
        runTest("test2", 1);
        runTest("test2", 101);
    }

}

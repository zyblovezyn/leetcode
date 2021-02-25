/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

/**
 * Inspired by {@code com.sun.media.sound.DirectAudioDevice$DirectDL.drain()}.
 *
 * Two loop exits hold a monitor while merging.
 *
 */
public final class SynchronizedLoopExit01 extends JTTTest {

    @Rule public TestRule timeout = createTimeoutSeconds(20);

    protected Object object = new Object();
    protected volatile boolean drained = false;
    protected volatile boolean someBoolean = true;
    protected volatile int someInt = 3;

    public boolean test() {
        boolean b = true;
        while (!drained) {
            synchronized (object) {
                boolean c = b = someBoolean;
                if (c || drained) {
                    break;
                }
            }
        }
        return b;
    }

    @Test
    public void run0() throws Throwable {
        runTest("test");
    }

    public synchronized boolean test1() {
        boolean b = true;
        while (!drained) {
            synchronized (object) {
                boolean c = b = someBoolean;
                if (c || drained) {
                    break;
                }
            }
        }
        return b;
    }

    @Test
    public void run1() throws Throwable {
        runTest("test1");
    }

    public synchronized boolean test2() {
        boolean b = true;
        while (!drained) {
            synchronized (object) {
                boolean c = b = someBoolean;
                if (c || drained) {
                    break;
                }
                if (someInt > 0) {
                    throw new RuntimeException();
                }
            }
            if (someInt < -10) {
                throw new IndexOutOfBoundsException();
            }
        }
        if (someInt < -5) {
            throw new IllegalArgumentException();
        }
        return b;
    }

    @Test
    public void run2() throws Throwable {
        runTest("test2");
    }

}

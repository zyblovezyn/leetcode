/*
 * Copyright (c) 2007, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.jtt.jdk;

import org.graalvm.compiler.api.directives.GraalDirectives;
import org.graalvm.compiler.jtt.JTTTest;
import org.junit.Test;

/**
 * Checks that the time between 2 successive calls to {@link System#currentTimeMillis()} is less
 * than 100 milliseconds at least once in 5_000_000 attempts.
 */
public class System_currentTimeMillis02 extends JTTTest {

    public static boolean test() {
        for (int i = 0; i < 5_000_000; i++) {
            long elapsed = System.currentTimeMillis() - System.currentTimeMillis();
            if (elapsed < 100) {
                return true;
            }
        }
        if (!GraalDirectives.inCompiledCode()) {
            // We don't care about the result for the interpreter, C1 or C2
            return true;
        }
        return false;
    }

    @Test
    public void run0() throws Throwable {
        runTest("test");
    }

}

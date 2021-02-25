/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.jdk9.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import org.graalvm.compiler.replacements.test.MethodSubstitutionTest;
import org.junit.Test;

public class MethodHandleImplTest extends MethodSubstitutionTest {

    static final MethodHandle squareHandle;
    static {
        try {
            squareHandle = MethodHandles.lookup().findStatic(MethodHandleImplTest.class, "square", MethodType.methodType(int.class, int.class));
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public static int square(int a) {
        return a * a;
    }

    public static int invokeSquare() {
        try {
            return (int) squareHandle.invokeExact(6);
        } catch (Throwable e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Test {@code MethodHandleImpl.isCompileConstant} by effect: If it is not intrinsified,
     * {@code Invoke#Invokers.maybeCustomize(mh)} will appear in the graph.
     */
    @Test
    public void testIsCompileConstant() {
        test("invokeSquare");
        testGraph("invokeSquare");
    }

}

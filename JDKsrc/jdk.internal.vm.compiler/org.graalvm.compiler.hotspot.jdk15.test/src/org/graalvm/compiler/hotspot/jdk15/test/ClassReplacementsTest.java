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


package org.graalvm.compiler.hotspot.jdk15.test;

import org.graalvm.compiler.replacements.test.MethodSubstitutionTest;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * As of JDK 15 {@code java.lang.Class::isHidden()} was added.
 *
 * @see "https://openjdk.java.net/jeps/371"
 * @see "https://bugs.openjdk.java.net/browse/JDK-8238358"
 */
public class ClassReplacementsTest extends MethodSubstitutionTest {

    @SuppressWarnings("all")
    public static boolean isHidden(Class<?> clazz) {
        return clazz.isHidden();
    }

    @Test
    public void testIsHidden() {
        testGraph("isHidden");

        Supplier<Runnable> lambda = () -> () -> System.out.println("run");

        for (Class<?> c : new Class<?>[]{getClass(), Cloneable.class, int[].class, String[][].class, lambda.getClass(), lambda.get().getClass()}) {
            test("isHidden", c);
        }
    }

}

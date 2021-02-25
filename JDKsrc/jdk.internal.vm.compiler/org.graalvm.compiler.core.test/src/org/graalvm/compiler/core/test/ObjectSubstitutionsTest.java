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


package org.graalvm.compiler.core.test;

import org.junit.Test;

public class ObjectSubstitutionsTest extends GraalCompilerTest {

    public static int SideEffect;

    public static final void notifySnippet() {
        synchronized (ObjectSubstitutionsTest.class) {
            SideEffect = System.identityHashCode(ObjectSubstitutionsTest.class);
            ObjectSubstitutionsTest.class.notify();
        }
    }

    public static final void notifyAllSnippet() {
        synchronized (ObjectSubstitutionsTest.class) {
            SideEffect = System.identityHashCode(ObjectSubstitutionsTest.class);
            ObjectSubstitutionsTest.class.notifyAll();
        }
    }

    @Test
    public void testNotifyEmpty() {
        test("notifySnippet");
    }

    @Test
    public void testNotifyAllEmpty() {
        test("notifyAllSnippet");
    }

}

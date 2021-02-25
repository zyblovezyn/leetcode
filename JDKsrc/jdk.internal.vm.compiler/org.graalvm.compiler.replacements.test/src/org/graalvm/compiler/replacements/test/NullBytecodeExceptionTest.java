/*
 * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.test;

import org.junit.Test;

public class NullBytecodeExceptionTest extends BytecodeExceptionTest {

    public static void nullSnippet(Object obj) {
        obj.toString();
    }

    @Test
    public void testNullPointerException() {
        test("nullSnippet", (Object) null);
    }
}

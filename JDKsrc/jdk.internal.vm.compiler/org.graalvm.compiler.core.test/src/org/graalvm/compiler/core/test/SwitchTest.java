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

import jdk.vm.ci.meta.ResolvedJavaMethod;
import org.junit.Test;

// Regression test for JDK-8220643 (GR-14583)
public class SwitchTest extends GraalCompilerTest {
    public static boolean test1(int arg) {
        switch (arg) {
            case -2139290527:
            case -1466249004:
            case -1063407861:
            case 125135499:
            case 425995464:
            case 786490581:
            case 1180611932:
            case 1790655921:
            case 1970660086:
                return true;
            default:
                return false;
        }
    }

    @Test
    public void run1() throws Throwable {
        ResolvedJavaMethod method = getResolvedJavaMethod("test1");
        Result compiled = executeActual(method, null, -2139290527);
        assertEquals(new Result(true, null), compiled);
    }
}

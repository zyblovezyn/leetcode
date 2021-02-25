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


package org.graalvm.compiler.core.test;

import static org.graalvm.compiler.api.directives.GraalDirectives.injectBranchProbability;

import org.junit.Test;

/**
 * Test extracted from reproducer in https://github.com/oracle/graal/issues/2493.
 */
public class IfCanonicalizerSwapTest extends GraalCompilerTest {
    public static String testSnippet1(long value) {
        if (injectBranchProbability(0.50, value >= 0L) && injectBranchProbability(0.00, value <= 35L)) {
            return "JustRight";
        } else {
            if (injectBranchProbability(0.50, value > 35L)) {
                return "TooHot";
            } else {
                return "TooCold";
            }
        }
    }

    @Test
    public void test1() {
        test("testSnippet1", -1L);
        test("testSnippet1", 100L);
        test("testSnippet1", 10L);
    }
}

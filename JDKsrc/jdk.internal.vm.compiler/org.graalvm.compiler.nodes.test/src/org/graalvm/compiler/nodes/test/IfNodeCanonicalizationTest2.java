/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Arm Limited. All rights reserved.
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



package org.graalvm.compiler.nodes.test;

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.calc.ConditionalNode;
import org.graalvm.compiler.options.OptionValues;
import org.graalvm.compiler.phases.Phase;
import org.graalvm.compiler.phases.tiers.Suites;
import org.junit.Assert;
import org.junit.Test;

public class IfNodeCanonicalizationTest2 extends GraalCompilerTest {
    private StructuredGraph structuredGraph;

    @Override
    protected Suites createSuites(OptionValues opts) {
        Suites suites = super.createSuites(opts);
        suites.getLowTier().appendPhase(new Phase() {
            @Override
            protected void run(StructuredGraph graph) {
                structuredGraph = graph;
            }
        });
        return suites;
    }

    /**
     * Math.min() tests for integer type, which can be simplified to conditional move.
     */
    public int minInt(int m, int n) {
        return Math.min(m, n);
    }

    @Test
    public void testMinInt() {
        test("minInt", 1, 2);
        checkConditionalNode("minInt", 1);
    }

    public long minLong(long m, long n) {
        return Math.min(m, n);
    }

    @Test
    public void testMinLong() {
        test("minLong", 1L, 2L);
        checkConditionalNode("minLong", 1);
    }

    /**
     * Math.max() tests for integer type, which can be simplified to conditional move.
     */
    public int maxInt(int m, int n) {
        return Math.max(m, n);
    }

    @Test
    public void testMaxInt() {
        test("maxInt", 1, 2);
        checkConditionalNode("maxInt", 1);
    }

    public long maxLong(long m, long n) {
        return Math.max(m, n);
    }

    @Test
    public void testMaxLong() {
        test("maxLong", 1L, 2L);
        checkConditionalNode("maxLong", 1);
    }

    /**
     * General min test for integer type, which can be simplified to conditional move.
     */
    public int generalMin(int m, int n) {
        int value;
        if (m <= n) {
            value = m;
        } else {
            value = n;
        }
        return 2 * value;
    }

    @Test
    public void testGeneralMin() {
        test("generalMin", 1, 2);
        checkConditionalNode("generalMin", 1);
    }

    /**
     * General max test for integer type, which can be simplified to conditional move.
     */
    public int generalMax(int m, int n) {
        if (m >= n) {
            return m;
        }
        return n;
    }

    @Test
    public void testGeneralMax() {
        test("generalMax", 1, 2);
        checkConditionalNode("generalMax", 1);
    }

    /**
     * General conditional set tests for integer type, which can be simplified to conditional move.
     */
    public int integerEqualsCondMove(int m, int n, int a, int b) {
        if (m == n) {
            return a;
        }
        return b;
    }

    @Test
    public void testIntegerEqualsCondMove() {
        test("integerEqualsCondMove", 1, 2, 2, 4);
        checkConditionalNode("integerEqualsCondMove", 1);
    }

    public int isNullCondMove(Object obj, int m, int n) {
        if (obj == null) {
            return m;
        }
        return n;
    }

    @Test
    public void testIsNullCondMove() {
        test("isNullCondMove", new Object(), 1, 2);
        checkConditionalNode("isNullCondMove", 1);
    }

    private void checkConditionalNode(String methodName, int expected) {
        compile(getResolvedJavaMethod(methodName), null);
        int actual = structuredGraph.getNodes().filter(ConditionalNode.class).count();
        Assert.assertEquals(expected, actual);
    }
}

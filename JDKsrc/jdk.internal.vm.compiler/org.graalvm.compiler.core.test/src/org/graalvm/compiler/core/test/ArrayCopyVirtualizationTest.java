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

import org.junit.Test;

import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.java.NewArrayNode;

public class ArrayCopyVirtualizationTest extends GraalCompilerTest {

    @Override
    protected void checkMidTierGraph(StructuredGraph graph) {
        assertTrue(graph.getNodes().filter(node -> node instanceof NewArrayNode).count() == 0, "shouldn't require allocation in %s", graph);
        super.checkMidTierGraph(graph);
    }

    public byte byteCopyVirtualization() {
        byte[] array = new byte[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }

    public short shortCopyVirtualization() {
        short[] array = new short[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 2);
        return array[0];
    }

    public char charCopyVirtualization() {
        char[] array = new char[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }

    public int intCopyVirtualization() {
        int[] array = new int[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }

    public long longCopyVirtualization() {
        long[] array = new long[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }

    public float floatCopyVirtualization() {
        float[] array = new float[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }

    public double doubleCopyVirtualization() {
        double[] array = new double[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }

    public Object objectCopyVirtualization() {
        Object[] array = new Object[]{1, 2, 3, 4};
        System.arraycopy(array, 1, array, 0, 3);
        return array[0];
    }

    @Test
    public void testCopyVirtualization() {
        test("byteCopyVirtualization");
        test("shortCopyVirtualization");
        test("charCopyVirtualization");
        test("intCopyVirtualization");
        test("longCopyVirtualization");
        test("floatCopyVirtualization");
        test("doubleCopyVirtualization");
        test("objectCopyVirtualization");
    }

    public byte byteCopyBackwardsVirtualization() {
        byte[] array = new byte[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    public short shortCopyBackwardsVirtualization() {
        short[] array = new short[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    public char charCopyBackwardsVirtualization() {
        char[] array = new char[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    public int intCopyBackwardsVirtualization() {
        int[] array = new int[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    public long longCopyBackwardsVirtualization() {
        long[] array = new long[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    public float floatCopyBackwardsVirtualization() {
        float[] array = new float[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    public double doubleCopyBackwardsVirtualization() {
        double[] array = new double[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    public Object objectCopyBackwardsVirtualization() {
        Object[] array = new Object[]{1, 2, 3, 4};
        System.arraycopy(array, 0, array, 1, 3);
        return array[3];
    }

    @Test
    public void testCopyBackwardsVirtualization() {
        test("byteCopyBackwardsVirtualization");
        test("shortCopyBackwardsVirtualization");
        test("charCopyBackwardsVirtualization");
        test("intCopyBackwardsVirtualization");
        test("longCopyBackwardsVirtualization");
        test("floatCopyBackwardsVirtualization");
        test("doubleCopyBackwardsVirtualization");
        test("objectCopyBackwardsVirtualization");
    }
}

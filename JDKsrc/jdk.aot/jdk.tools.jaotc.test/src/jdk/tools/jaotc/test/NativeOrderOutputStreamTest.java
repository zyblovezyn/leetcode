/*
 * Copyright (c) 2016, 2019, Oracle and/or its affiliates. All rights reserved.
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

/**
 * @test
 * @requires vm.aot
 * @modules jdk.aot/jdk.tools.jaotc.utils
 * @run junit/othervm -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI jdk.tools.jaotc.test.NativeOrderOutputStreamTest
 */



package jdk.tools.jaotc.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Assert;
import org.junit.Test;

import jdk.tools.jaotc.utils.NativeOrderOutputStream;

public class NativeOrderOutputStreamTest {

    @Test
    public void shouldAdd4BytesForInt() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        target.putInt(5);
        Assert.assertEquals(4, target.position());
    }

    @Test
    public void shouldAdd8BytesForLong() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        target.putLong(8);
        Assert.assertEquals(8, target.position());
    }

    @Test
    public void shouldHaveCorrectSizeBeforePatch() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        target.patchableInt();
        Assert.assertEquals(4, target.position());
    }

    @Test
    public void shouldHaveCorrectSizeAfterPatch() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        NativeOrderOutputStream.PatchableInt patchableInt = target.patchableInt();
        patchableInt.set(12);
        Assert.assertEquals(4, target.position());
    }

    @Test
    public void shouldSetCorrectValueInPatch() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        NativeOrderOutputStream.PatchableInt patchableInt = target.patchableInt();
        patchableInt.set(42);
        Assert.assertEquals(42, getInt(target, 0));
    }

    private static int getInt(NativeOrderOutputStream target, int pos) {
        ByteBuffer buffer = ByteBuffer.wrap(target.array());
        buffer.order(ByteOrder.nativeOrder());
        return buffer.getInt(pos);
    }

    @Test
    public void shouldPutArrayCorrectly() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        target.put(new byte[]{42, 5, 43, 44});
        Assert.assertEquals(4, target.position());
        Assert.assertEquals(42, target.array()[0]);
        Assert.assertEquals(4, target.position());
    }

    @Test
    public void shouldOnlyPatchSlot() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        NativeOrderOutputStream.PatchableInt patchableInt = target.patchableInt();
        target.putInt(7);
        patchableInt.set(39);
        Assert.assertEquals(39, getInt(target, 0));
        Assert.assertEquals(7, getInt(target, 4));
    }

    @Test
    public void shouldBeAbleToPatchAnywhere() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        target.putInt(19);
        NativeOrderOutputStream.PatchableInt patchableInt = target.patchableInt();
        patchableInt.set(242);

        Assert.assertEquals(19, getInt(target, 0));
        Assert.assertEquals(242, getInt(target, 4));
    }

    @Test
    public void shouldHavePatchableAtRightOffset() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        target.putInt(27);
        Assert.assertEquals(4, target.position());
        NativeOrderOutputStream.PatchableInt patchableInt = target.patchableInt();
        Assert.assertEquals(4, patchableInt.position());
    }

    @Test
    public void shouldAlign() {
        NativeOrderOutputStream target = new NativeOrderOutputStream();
        target.putInt(9);
        target.align(16);
        target.put(new byte[]{3});
        target.align(8);
        Assert.assertEquals(24, target.position());
    }
}

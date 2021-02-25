/*
 *  Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *  ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
 *
 *
 *
 */

package jdk.internal.foreign;

import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
import jdk.internal.access.JavaNioAccess;
import jdk.internal.access.SharedSecrets;
import jdk.internal.misc.Unsafe;
import jdk.internal.vm.annotation.ForceInline;
import sun.security.action.GetBooleanAction;

import java.nio.ByteBuffer;

/**
 * Implementation for native memory segments. A native memory segment is essentially a wrapper around
 * a native long address.
 */
public class NativeMemorySegmentImpl extends AbstractMemorySegmentImpl {

    private static final Unsafe unsafe = Unsafe.getUnsafe();

    // The maximum alignment supported by malloc - typically 16 on
    // 64-bit platforms and 8 on 32-bit platforms.
    private final static long MAX_ALIGN = Unsafe.ADDRESS_SIZE == 4 ? 8 : 16;

    private static final boolean skipZeroMemory = GetBooleanAction.privilegedGetProperty("jdk.internal.foreign.skipZeroMemory");

    final long min;

    @ForceInline
    NativeMemorySegmentImpl(long min, long length, int mask, MemoryScope scope) {
        super(length, mask, scope);
        this.min = min;
    }

    @Override
    NativeMemorySegmentImpl dup(long offset, long size, int mask, MemoryScope scope) {
        return new NativeMemorySegmentImpl(min + offset, size, mask, scope);
    }

    @Override
    ByteBuffer makeByteBuffer() {
        JavaNioAccess nioAccess = SharedSecrets.getJavaNioAccess();
        return nioAccess.newDirectByteBuffer(min(), (int) this.length, null, this);
    }

    @Override
    long min() {
        return min;
    }

    @Override
    Object base() {
        return null;
    }

    // factories

    public static MemorySegment makeNativeSegment(long bytesSize, long alignmentBytes) {
        long alignedSize = bytesSize;

        if (alignmentBytes > MAX_ALIGN) {
            alignedSize = bytesSize + (alignmentBytes - 1);
        }

        long buf = unsafe.allocateMemory(alignedSize);
        if (!skipZeroMemory) {
            unsafe.setMemory(buf, alignedSize, (byte)0);
        }
        long alignedBuf = Utils.alignUp(buf, alignmentBytes);
        MemoryScope scope = MemoryScope.create(null, () -> unsafe.freeMemory(buf));
        MemorySegment segment = new NativeMemorySegmentImpl(buf, alignedSize,
                defaultAccessModes(alignedSize), scope);
        if (alignedSize != bytesSize) {
            long delta = alignedBuf - buf;
            segment = segment.asSlice(delta, bytesSize);
        }
        return segment;
    }

    public static MemorySegment makeNativeSegmentUnchecked(MemoryAddress min, long bytesSize, Thread owner, Runnable cleanup, Object attachment) {
        MemoryScope scope = MemoryScope.createUnchecked(owner, attachment, cleanup);
        return new NativeMemorySegmentImpl(min.toRawLongValue(), bytesSize, defaultAccessModes(bytesSize), scope);
    }
}

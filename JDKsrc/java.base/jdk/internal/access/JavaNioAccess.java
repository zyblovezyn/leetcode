/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
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
 *
 *
 */

package jdk.internal.access;

import jdk.internal.access.foreign.MemorySegmentProxy;
import jdk.internal.access.foreign.UnmapperProxy;
import jdk.internal.misc.VM.BufferPool;

import java.io.FileDescriptor;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public interface JavaNioAccess {

    /**
     * Used by {@code jdk.internal.misc.VM}.
     */
    BufferPool getDirectBufferPool();

    /**
     * Constructs a direct ByteBuffer referring to the block of memory starting
     * at the given memory address and extending {@code cap} bytes.
     * The {@code ob} parameter is an arbitrary object that is attached
     * to the resulting buffer.
     * Used by {@code jdk.internal.foreignMemorySegmentImpl}.
     */
    ByteBuffer newDirectByteBuffer(long addr, int cap, Object obj, MemorySegmentProxy segment);

    /**
     * Constructs a mapped ByteBuffer referring to the block of memory starting
     * at the given memory address and extending {@code cap} bytes.
     * The {@code ob} parameter is an arbitrary object that is attached
     * to the resulting buffer. The {@code sync} and {@code fd} parameters of the mapped
     * buffer are derived from the {@code UnmapperProxy}.
     * Used by {@code jdk.internal.foreignMemorySegmentImpl}.
     */
    ByteBuffer newMappedByteBuffer(UnmapperProxy unmapperProxy, long addr, int cap, Object obj, MemorySegmentProxy segment);

    /**
     * Constructs an heap ByteBuffer with given backing array, offset, capacity and segment.
     * Used by {@code jdk.internal.foreignMemorySegmentImpl}.
     */
    ByteBuffer newHeapByteBuffer(byte[] hb, int offset, int capacity, MemorySegmentProxy segment);

    /**
     * Used by {@code jdk.internal.foreign.Utils}.
     */
    Object getBufferBase(ByteBuffer bb);

    /**
     * Used by {@code jdk.internal.foreign.Utils}.
     */
    long getBufferAddress(ByteBuffer bb);

    /**
     * Used by {@code jdk.internal.foreign.Utils}.
     */
    UnmapperProxy unmapper(ByteBuffer bb);

    /**
     * Used by {@code jdk.internal.foreign.AbstractMemorySegmentImpl} and byte buffer var handle views.
     */
    MemorySegmentProxy bufferSegment(Buffer buffer);

    /**
     * Used by {@code jdk.internal.foreign.MappedMemorySegmentImpl} and byte buffer var handle views.
     */
    void force(FileDescriptor fd, long address, boolean isSync, long offset, long size);

    /**
     * Used by {@code jdk.internal.foreign.MappedMemorySegmentImpl} and byte buffer var handle views.
     */
    void load(long address, boolean isSync, long size);

    /**
     * Used by {@code jdk.internal.foreign.MappedMemorySegmentImpl}.
     */
    void unload(long address, boolean isSync, long size);

    /**
     * Used by {@code jdk.internal.foreign.MappedMemorySegmentImpl} and byte buffer var handle views.
     */
    boolean isLoaded(long address, boolean isSync, long size);
}

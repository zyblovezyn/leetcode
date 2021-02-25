/*
 * Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.
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

// -- This file was mechanically generated: Do not edit! -- //

package java.nio;

import java.io.FileDescriptor;
import java.lang.ref.Reference;
import java.util.Objects;
import jdk.internal.access.foreign.MemorySegmentProxy;
import jdk.internal.misc.VM;
import jdk.internal.ref.Cleaner;
import sun.nio.ch.DirectBuffer;


class DirectFloatBufferU

    extends FloatBuffer



    implements DirectBuffer
{



    // Cached array base offset
    private static final long ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(float[].class);

    // Cached unaligned-access capability
    protected static final boolean UNALIGNED = Bits.unaligned();

    // Base address, used in all indexing calculations
    // NOTE: moved up to Buffer.java for speed in JNI GetDirectBufferAddress
    //    protected long address;

    // An object attached to this buffer. If this buffer is a view of another
    // buffer then we use this field to keep a reference to that buffer to
    // ensure that its memory isn't freed before we are done with it.
    private final Object att;

    public Object attachment() {
        return att;
    }




































    public Cleaner cleaner() { return null; }




























































































    // For duplicates and slices
    //
    DirectFloatBufferU(DirectBuffer db,         // package-private
                               int mark, int pos, int lim, int cap,
                               int off, MemorySegmentProxy segment)
    {

        super(mark, pos, lim, cap, segment);
        address = db.address() + off;



        Object attachment = db.attachment();
        att = (attachment == null ? db : attachment);




    }

    @Override
    Object base() {
        return null;
    }

    public FloatBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 2);
        assert (off >= 0);
        return new DirectFloatBufferU(this, -1, 0, rem, rem, off, segment);
    }

    @Override
    public FloatBuffer slice(int index, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        return new DirectFloatBufferU(this,
                                              -1,
                                              0,
                                              length,
                                              length,
                                              index, segment);
    }

    public FloatBuffer duplicate() {
        return new DirectFloatBufferU(this,
                                              this.markValue(),
                                              this.position(),
                                              this.limit(),
                                              this.capacity(),
                                              0, segment);
    }

    public FloatBuffer asReadOnlyBuffer() {

        return new DirectFloatBufferRU(this,
                                           this.markValue(),
                                           this.position(),
                                           this.limit(),
                                           this.capacity(),
                                           0, segment);



    }



    public long address() {
        return address;
    }

    private long ix(int i) {
        return address + ((long)i << 2);
    }

    public float get() {
        try {
            checkSegment();
            return ((UNSAFE.getFloat(ix(nextGetIndex()))));
        } finally {
            Reference.reachabilityFence(this);
        }
    }

    public float get(int i) {
        try {
            checkSegment();
            return ((UNSAFE.getFloat(ix(checkIndex(i)))));
        } finally {
            Reference.reachabilityFence(this);
        }
    }











    public FloatBuffer get(float[] dst, int offset, int length) {

        checkSegment();
        if (((long)length << 2) > Bits.JNI_COPY_TO_ARRAY_THRESHOLD) {
            Objects.checkFromIndexSize(offset, length, dst.length);
            int pos = position();
            int lim = limit();
            assert (pos <= lim);
            int rem = (pos <= lim ? lim - pos : 0);
            if (length > rem)
                throw new BufferUnderflowException();

            long dstOffset = ARRAY_BASE_OFFSET + ((long)offset << 2);
            try {

                if (order() != ByteOrder.nativeOrder())
                    UNSAFE.copySwapMemory(null,
                                          ix(pos),
                                          dst,
                                          dstOffset,
                                          (long)length << 2,
                                          (long)1 << 2);
                else

                    UNSAFE.copyMemory(null,
                                      ix(pos),
                                      dst,
                                      dstOffset,
                                      (long)length << 2);
            } finally {
                Reference.reachabilityFence(this);
            }
            position(pos + length);
        } else {
            super.get(dst, offset, length);
        }
        return this;



    }

    public FloatBuffer get(int index, float[] dst, int offset, int length) {

        checkSegment();
        if (((long)length << 2) > Bits.JNI_COPY_TO_ARRAY_THRESHOLD) {
            Objects.checkFromIndexSize(index, length, limit());
            Objects.checkFromIndexSize(offset, length, dst.length);

            long dstOffset = ARRAY_BASE_OFFSET + ((long)offset << 2);
            try {

                if (order() != ByteOrder.nativeOrder())
                    UNSAFE.copySwapMemory(null,
                                          ix(index),
                                          dst,
                                          dstOffset,
                                          (long)length << 2,
                                          (long)1 << 2);
                else

                    UNSAFE.copyMemory(null,
                                      ix(index),
                                      dst,
                                      dstOffset,
                                      (long)length << 2);
            } finally {
                Reference.reachabilityFence(this);
            }
        } else {
            super.get(index, dst, offset, length);
        }
        return this;



    }


    public FloatBuffer put(float x) {

        try {
            checkSegment();
            UNSAFE.putFloat(ix(nextPutIndex()), ((x)));
        } finally {
            Reference.reachabilityFence(this);
        }
        return this;



    }

    public FloatBuffer put(int i, float x) {

        try {
            checkSegment();
            UNSAFE.putFloat(ix(checkIndex(i)), ((x)));
        } finally {
            Reference.reachabilityFence(this);
        }
        return this;



    }

    public FloatBuffer put(FloatBuffer src) {

        checkSegment();
        super.put(src);
        return this;



    }

    public FloatBuffer put(float[] src, int offset, int length) {

        checkSegment();
        if (((long)length << 2) > Bits.JNI_COPY_FROM_ARRAY_THRESHOLD) {
            Objects.checkFromIndexSize(offset, length, src.length);
            int pos = position();
            int lim = limit();
            assert (pos <= lim);
            int rem = (pos <= lim ? lim - pos : 0);
            if (length > rem)
                throw new BufferOverflowException();

            long srcOffset = ARRAY_BASE_OFFSET + ((long)offset << 2);
            try {

                if (order() != ByteOrder.nativeOrder())
                    UNSAFE.copySwapMemory(src,
                                          srcOffset,
                                          null,
                                          ix(pos),
                                          (long)length << 2,
                                          (long)1 << 2);
                else

                    UNSAFE.copyMemory(src,
                                      srcOffset,
                                      null,
                                      ix(pos),
                                      (long)length << 2);
            } finally {
                Reference.reachabilityFence(this);
            }
            position(pos + length);
        } else {
            super.put(src, offset, length);
        }
        return this;



    }

    public FloatBuffer put(int index, float[] src, int offset, int length) {

        checkSegment();
        if (((long)length << 2) > Bits.JNI_COPY_FROM_ARRAY_THRESHOLD) {
            Objects.checkFromIndexSize(index, length, limit());
            Objects.checkFromIndexSize(offset, length, src.length);


            long srcOffset = ARRAY_BASE_OFFSET + ((long)offset << 2);
            try {

                if (order() != ByteOrder.nativeOrder())
                    UNSAFE.copySwapMemory(src,
                                          srcOffset,
                                          null,
                                          ix(index),
                                          (long)length << 2,
                                          (long)1 << 2);
                else

                    UNSAFE.copyMemory(src,
                                      srcOffset,
                                      null,
                                      ix(index),
                                      (long)length << 2);
            } finally {
                Reference.reachabilityFence(this);
            }
        } else {
            super.put(index, src, offset, length);
        }
        return this;



    }

    public FloatBuffer compact() {

        int pos = position();
        int lim = limit();
        assert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);
        try {
            UNSAFE.copyMemory(ix(pos), ix(0), (long)rem << 2);
        } finally {
            Reference.reachabilityFence(this);
        }
        position(rem);
        limit(capacity());
        discardMark();
        return this;



    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return false;
    }













































    public ByteOrder order() {





        return ((ByteOrder.nativeOrder() != ByteOrder.BIG_ENDIAN)
                ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);

    }


















}

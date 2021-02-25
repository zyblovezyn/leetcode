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
 *
 *
 */

package java.lang.invoke;

/**
 * Base class for memory access var handle implementations.
 */
abstract class MemoryAccessVarHandleBase extends VarHandle {

    /** endianness **/
    final boolean be;

    /** access size (in bytes, computed from var handle carrier type) **/
    final long length;

    /** access offset (in bytes); must be compatible with {@code alignmentMask} **/
    final long offset;

    /** alignment constraint (in bytes, expressed as a bit mask) **/
    final long alignmentMask;

    MemoryAccessVarHandleBase(VarForm form, boolean be, long length, long offset, long alignmentMask) {
        super(form);
        this.be = be;
        this.length = length;
        this.offset = offset;
        this.alignmentMask = alignmentMask;
    }

    static IllegalStateException newIllegalStateExceptionForMisalignedAccess(long address) {
        return new IllegalStateException("Misaligned access at address: " + address);
    }

    /**
     * Strides used for multi-dimensional access; each stride must be compatible with {@code alignmentMask}.
     */
    abstract long[] strides();

    abstract Class<?> carrier();
}

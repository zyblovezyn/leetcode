/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core.common.util;

/**
 * Provides low-level sequential write access for signed and unsigned values of size 1, 2, 4, and 8
 * bytes.
 */
public interface TypeWriter {
    /**
     * Returns the number of bytes that have been written, i.e., the byte index of the next byte to
     * be written.
     */
    long getBytesWritten();

    /** Writes a signed 1 byte value. */
    void putS1(long value);

    /** Writes an unsigned 1 byte value. */
    void putU1(long value);

    /** Writes a signed 2 byte value. */
    void putS2(long value);

    /** Writes an unsigned 2 byte value. */
    void putU2(long value);

    /** Writes a signed 4 byte value. */
    void putS4(long value);

    /** Patches a previously written signed 4 byte value at a given offset. */
    void patchS4(long value, long offset);

    /** Writes an unsigned 4 byte value. */
    void putU4(long value);

    /** Writes a signed 8 byte value. */
    void putS8(long value);

    /** Writes a signed value in a variable byte size encoding. */
    void putSV(long value);

    /** Writes an unsigned value in a variable byte size encoding. */
    void putUV(long value);
}

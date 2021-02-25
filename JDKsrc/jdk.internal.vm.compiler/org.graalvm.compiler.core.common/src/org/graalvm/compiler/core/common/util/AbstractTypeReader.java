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


package org.graalvm.compiler.core.common.util;

public abstract class AbstractTypeReader implements TypeReader {
    @Override
    public long getSV() {
        return decodeSign(read());
    }

    @Override
    public long getUV() {
        return read();
    }

    public static long decodeSign(long value) {
        return (value >>> 1) ^ -(value & 1);
    }

    private long read() {
        int b0 = getU1();
        if (b0 < UnsafeArrayTypeWriter.NUM_LOW_CODES) {
            return b0;
        } else {
            return readPacked(b0);
        }
    }

    private long readPacked(int b0) {
        assert b0 >= UnsafeArrayTypeWriter.NUM_LOW_CODES;
        long sum = b0;
        long shift = UnsafeArrayTypeWriter.HIGH_WORD_SHIFT;
        for (int i = 2;; i++) {
            long b = getU1();
            sum += b << shift;
            if (b < UnsafeArrayTypeWriter.NUM_LOW_CODES || i == UnsafeArrayTypeWriter.MAX_BYTES) {
                return sum;
            }
            shift += UnsafeArrayTypeWriter.HIGH_WORD_SHIFT;
        }
    }
}

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


package org.graalvm.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A stream that can read (trivial) values using their in-band data type information, intended for
 * use with {@link TypedDataOutputStream}.
 */
public class TypedDataInputStream extends DataInputStream {
    public TypedDataInputStream(InputStream in) {
        super(in);
    }

    /**
     * Reads a single value, using the data type encoded in the stream.
     *
     * @return The read value, such as a boxed primitive or a {@link String}.
     * @exception IOException in case of an I/O error.
     */
    public Object readTypedValue() throws IOException {
        Object value;
        final byte type = readByte();
        switch (type) {
            case 'Z':
                value = readBoolean();
                break;
            case 'B':
                value = readByte();
                break;
            case 'S':
                value = readShort();
                break;
            case 'C':
                value = readChar();
                break;
            case 'I':
                value = readInt();
                break;
            case 'J':
                value = readLong();
                break;
            case 'F':
                value = readFloat();
                break;
            case 'D':
                value = readDouble();
                break;
            case 'U':
                value = readUTF();
                break;
            default:
                throw new IOException("Unsupported type: " + Integer.toHexString(type));
        }
        return value;
    }
}

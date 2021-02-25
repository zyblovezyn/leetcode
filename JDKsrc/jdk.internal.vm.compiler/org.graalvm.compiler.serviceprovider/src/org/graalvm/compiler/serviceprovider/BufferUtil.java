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


package org.graalvm.compiler.serviceprovider;

import java.nio.Buffer;

/**
 * Covariant return types for some methods in the {@code java.nio.Buffer} were
 * <a href="https://bugs.openjdk.java.net/browse/JDK-4774077">introduced in JDK 9</a>.
 *
 * If calls to these methods are compiled with javac from JDK 9+ using {@code -target 8 -source 8}
 * then the call sites will invoke the covariant methods in the subclass. For example:
 *
 * <pre>
 * static void reset(ByteBuffer buf) {
 *     buf.reset();
 * }
 * </pre>
 *
 * will result in:
 *
 * <pre>
 *    0: aload_0
 *    1: invokevirtual #7  // Method java/nio/ByteBuffer.reset:()Ljava/nio/ByteBuffer;
 *    4: pop
 *    5: return
 * </pre>
 *
 * This will result in a {@link NoSuchMethodError} when run on JDK 8. The workaround for this is to
 * {@linkplain #asBaseBuffer(Buffer) coerce} the receiver for calls to the covariant methods to
 * {@link Buffer}.
 */
public final class BufferUtil {

    /**
     * Coerces {@code obj} to be of type {@link Buffer}. This is required instead of a cast as
     * {@code org.graalvm.compiler.core.test.VerifyBufferUsage} is based on Graal graphs which will
     * have had redundant casts eliminated by the bytecode parser.
     */
    public static Buffer asBaseBuffer(Buffer obj) {
        return obj;
    }
}

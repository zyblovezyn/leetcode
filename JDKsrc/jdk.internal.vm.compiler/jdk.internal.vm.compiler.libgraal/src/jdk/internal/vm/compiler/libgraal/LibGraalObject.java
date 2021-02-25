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


package jdk.internal.vm.compiler.libgraal;

/**
 * Encapsulates a handle to an object in a libgraal isolate where the object's lifetime is bound to
 * the lifetime of the {@link LibGraalObject} instance. At some point after a {@link LibGraalObject}
 * is garbage collected, a call is made to release the handle, allowing the libgraal object to be
 * collected.
 */
public class LibGraalObject {

    static {
        if (LibGraal.isAvailable()) {
            LibGraal.registerNativeMethods(LibGraalObject.class);
        }
    }

    /**
     * Handle to an object in {@link #isolate}.
     */
    private final long handle;

    /**
     * The libgraal isolate containing {@link #handle}.
     */
    private final LibGraalIsolate isolate;

    /**
     * Creates a new {@link LibGraalObject}.
     *
     * @param handle handle to an object in a libgraal isolate
     */
    protected LibGraalObject(long handle) {
        this.handle = handle;
        isolate = LibGraalScope.current().getIsolate();
        isolate.register(this, handle);
    }

    /**
     * Gets the raw JNI handle wrapped by this object.
     *
     * @throw {@link IllegalArgumentException} if the isolate context for the handle has destroyed.
     */
    public long getHandle() {
        if (!isolate.isValid()) {
            throw new IllegalArgumentException(toString());
        }
        return handle;
    }

    /**
     * Releases {@code handle} in the isolate denoted by {@code isolateThreadId}.
     *
     * @return {@code false} if {@code} is not a valid handle in the isolate
     */
    // Implementation:
    // com.oracle.svm.graal.hotspot.libgraal.LibGraalEntryPoints.releaseHandle
    static native boolean releaseHandle(long isolateThreadId, long handle);

    @Override
    public String toString() {
        String name = getClass().getSimpleName();
        Class<?> outer = getClass().getDeclaringClass();
        while (outer != null) {
            name = outer.getSimpleName() + '.' + name;
            outer = outer.getDeclaringClass();
        }
        return String.format("%s[%d]", name, handle);
    }
}

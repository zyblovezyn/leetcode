/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
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

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Access to the {@link Unsafe} capability. Care must be taken not to leak the {@link #UNSAFE} value
 * of out code loaded by the JVMCI class loader or encapsulated in the JVMCI or Graal modules into
 * other code (e.g. via the Polyglot API).
 */
public class GraalUnsafeAccess {

    private static final Unsafe UNSAFE = initUnsafe();

    private static Unsafe initUnsafe() {
        try {
            // Fast path when we are trusted.
            return Unsafe.getUnsafe();
        } catch (SecurityException se) {
            // Slow path when we are not trusted.
            try {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(Unsafe.class);
            } catch (Exception e) {
                throw new RuntimeException("exception while trying to get Unsafe", e);
            }
        }
    }

    /**
     * Gets the {@link Unsafe} singleton.
     *
     * @throws SecurityException if a security manager is present and it denies
     *             {@link RuntimePermission}("accessUnsafe")
     */
    public static Unsafe getUnsafe() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("accessUnsafe"));
        }
        return UNSAFE;
    }
}

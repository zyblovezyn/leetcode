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


package org.graalvm.compiler.serviceprovider;

/**
 * Utility methods that provide access to isolate details.
 */
public final class IsolateUtil {

    /**
     * Gets the address of the current isolate or 0 if this not an isolate-aware runtime.
     */
    public static long getIsolateAddress() {
        // Substituted by
        // com.oracle.svm.graal.Target_org_graalvm_compiler_serviceprovider_IsolateUtil
        return 0;
    }

    /**
     * Gets an identifier for the current isolate or 0 if this not an isolate-aware runtime. The
     * returned value is guaranteed to be unique for the first {@code 2^64 - 1} isolates in the
     * process.
     */
    public static long getIsolateID() {
        // Substituted by
        // com.oracle.svm.graal.Target_org_graalvm_compiler_serviceprovider_IsolateUtil
        return 0;
    }

    /**
     * Gets a string identifying the current isolate.
     *
     * If this is not an isolate-aware runtime, an empty string is returned.
     *
     * If {@code withAddress == true}, then
     * {@code String.format("%d@%x", getIsolateID(), getIsolateAddress())} is returned.
     *
     * Otherwise, {@code String.valueOf(getIsolateID())} is returned.
     */
    public static String getIsolateID(boolean withAddress) {
        long isolateAddress = getIsolateAddress();
        if (isolateAddress == 0) {
            return "";
        }
        if (withAddress) {
            return String.format("%d@%x", getIsolateID(), isolateAddress);
        }
        return String.valueOf(getIsolateID());
    }

    private IsolateUtil() {
    }
}

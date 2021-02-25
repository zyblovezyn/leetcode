/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core;

/**
 * This is a utility class for Threads started by the compiler itself. In certain execution
 * environments extra work must be done for these threads to execute correctly and this class
 * provides hooks for this work.
 */
public class GraalServiceThread extends Thread {
    private final Runnable runnable;

    public GraalServiceThread(Runnable runnable) {
        super();
        this.runnable = runnable;
    }

    @Override
    public final void run() {
        beforeRun();
        try {
            runnable.run();
        } finally {
            afterRun();
        }
    }

    /**
     * Substituted by {@code com.oracle.svm.graal.hotspot.libgraal.
     * Target_org_graalvm_compiler_core_GraalServiceThread} to attach to the peer runtime if
     * required.
     */
    private void afterRun() {
    }

    /**
     * Substituted by {@code com.oracle.svm.graal.hotspot.libgraal.
     * Target_org_graalvm_compiler_core_GraalServiceThread} to attach to the peer runtime if
     * required.
     */
    private void beforeRun() {
    }
}

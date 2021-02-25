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


package org.graalvm.compiler.core.common;

import jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * A profiling service that consumes compilation related events. The Java Flight Recorder (JFR) is
 * an example of such a service that can be exposed via this interface.
 */
public interface CompilerProfiler {

    /**
     * Get current value of the profiler's time counter.
     *
     * @return the number of profile-defined time units that have elapsed
     */
    long getTicks();

    /**
     * Notifies JFR when a compiler phase ends.
     *
     * @param compileId current computation unit id
     * @param startTime when the phase started
     * @param name name of the phase
     * @param nestingLevel how many ancestor phases there are of the phase
     */
    void notifyCompilerPhaseEvent(int compileId, long startTime, String name, int nestingLevel);

    /**
     * Notifies JFR when the compiler considers inlining {@code callee} into {@code caller}.
     *
     * @param compileId current computation unit id
     * @param caller caller method
     * @param callee callee method considered for inlining into {@code caller}
     * @param succeeded true if {@code callee} was inlined into {@code caller}
     * @param message extra information about inlining decision
     * @param bci byte code index of call site
     */
    void notifyCompilerInlingEvent(int compileId, ResolvedJavaMethod caller, ResolvedJavaMethod callee, boolean succeeded, String message, int bci);
}

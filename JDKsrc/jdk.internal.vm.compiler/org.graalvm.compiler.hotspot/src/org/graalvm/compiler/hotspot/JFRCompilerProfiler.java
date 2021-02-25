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


package org.graalvm.compiler.hotspot;

import org.graalvm.compiler.core.common.CompilerProfiler;
import org.graalvm.compiler.serviceprovider.ServiceProvider;

import jdk.vm.ci.hotspot.JFR;
import jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * A HotSpot JFR implementation of {@link CompilerProfiler}.
 */
@ServiceProvider(CompilerProfiler.class)
public final class JFRCompilerProfiler implements CompilerProfiler {

    @Override
    public long getTicks() {
        return JFR.Ticks.now();
    }

    @Override
    public void notifyCompilerPhaseEvent(int compileId, long startTime, String name, int nestingLevel) {
        JFR.CompilerPhaseEvent.write(startTime, name, compileId, nestingLevel);
    }

    @Override
    public void notifyCompilerInlingEvent(int compileId, ResolvedJavaMethod caller, ResolvedJavaMethod callee,
                    boolean succeeded, String message, int bci) {
        JFR.CompilerInliningEvent.write(compileId, caller, callee, succeeded, message, bci);
    }

}

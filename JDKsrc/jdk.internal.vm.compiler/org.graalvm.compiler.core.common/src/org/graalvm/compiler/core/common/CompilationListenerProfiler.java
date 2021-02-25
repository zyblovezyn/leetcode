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

import org.graalvm.compiler.debug.CompilationListener;
import org.graalvm.compiler.debug.DebugContext.CompilerPhaseScope;

import jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * Connects a {@link CompilationListener} to a {@link CompilerProfiler}.
 */
public class CompilationListenerProfiler implements CompilationListener {
    private final int compileId;
    private final CompilerProfiler profiler;

    /**
     * Creates a compilation listener that passes events for a specific compilation identified by
     * {@code compileId} onto {@code profiler}.
     */
    public CompilationListenerProfiler(CompilerProfiler profiler, int compileId) {
        this.profiler = profiler;
        this.compileId = compileId;
    }

    @Override
    public void notifyInlining(ResolvedJavaMethod caller, ResolvedJavaMethod callee, boolean succeeded, CharSequence message, int bci) {
        profiler.notifyCompilerInlingEvent(compileId, caller, callee, succeeded, message.toString(), bci);
    }

    @Override
    public CompilerPhaseScope enterPhase(CharSequence name, int nesting) {
        long start = profiler.getTicks();
        return new CompilerPhaseScope() {

            @Override
            public void close() {
                profiler.notifyCompilerPhaseEvent(compileId, start, name.toString(), nesting);
            }
        };
    }
}

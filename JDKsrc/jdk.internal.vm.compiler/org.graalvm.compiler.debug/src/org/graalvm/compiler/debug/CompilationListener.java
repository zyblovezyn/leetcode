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


package org.graalvm.compiler.debug;

import org.graalvm.compiler.debug.DebugContext.CompilerPhaseScope;

import jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * Implemented by clients interested in when the compiler starts/ends a {@linkplain #enterPhase
 * phase} or {@linkplain #notifyInlining considers inlining} a method.
 */
public interface CompilationListener {

    /**
     * Notifies this listener that the compiler is starting a compiler phase.
     *
     * @param name the name of the phase
     * @return an object whose {@link CompilerPhaseScope#close()} method will be called when the
     *         phase completes
     */
    CompilerPhaseScope enterPhase(CharSequence name, int nesting);

    /**
     * Notifies this listener when the compiler considers inlining {@code callee} into
     * {@code caller}.
     *
     * @param caller caller method
     * @param callee callee method considered for inlining into {@code caller}
     * @param succeeded true if {@code callee} was inlined into {@code caller}
     * @param message extra information about inlining decision
     * @param bci byte code index of call site
     */
    void notifyInlining(ResolvedJavaMethod caller, ResolvedJavaMethod callee, boolean succeeded, CharSequence message, int bci);
}

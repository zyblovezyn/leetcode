/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.phases;

import org.graalvm.compiler.nodes.StructuredGraph;

import jdk.vm.ci.meta.MetaAccessProvider;
import jdk.vm.ci.meta.ResolvedJavaType;

/***
 * Verifies a {@linkplain #verify graph} or {@linkplain #verifyClass class} against one or more
 * invariants.
 */
public abstract class VerifyPhase<C> extends BasePhase<C> {

    /**
     * Thrown when verification performed by a {@link VerifyPhase} fails.
     */
    @SuppressWarnings("serial")
    public static class VerificationError extends AssertionError {

        public VerificationError(String message) {
            super(message);
        }

        public VerificationError(String format, Object... args) {
            super(String.format(format, args));
        }

        public VerificationError(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @Override
    protected final void run(StructuredGraph graph, C context) {
        verify(graph, context);
    }

    /**
     * Checks {@code graph} against some invariants.
     *
     * @throws VerificationError if the verification fails
     */
    protected abstract void verify(StructuredGraph graph, C context);

    /**
     * Checks {@code clazz} against some invariants.
     *
     * @param clazz the class to verify
     * @param metaAccess an object to get a {@link ResolvedJavaType} for {@code clazz}
     * @throws VerificationError if the class violates some invariant
     */
    public void verifyClass(Class<?> clazz, MetaAccessProvider metaAccess) {
    }
}

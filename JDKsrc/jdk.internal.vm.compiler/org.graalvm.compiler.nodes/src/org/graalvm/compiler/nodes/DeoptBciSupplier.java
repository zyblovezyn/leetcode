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


package org.graalvm.compiler.nodes;

/**
 * A marker interface for nodes that represent operations that can deoptimize and thus need a BCI to
 * deopt to.
 */
public interface DeoptBciSupplier {

    /**
     * @return the byte code index (BCI) associated with the node implementing this interface
     */
    int bci();

    /**
     * Remember the byte code index (BCI) for code generation or lowering purposes. For example,
     * nodes lowering to foreign calls that can safepoint require a valid BCI for computations of
     * the during-state of such a foreign call.
     */
    @SuppressWarnings("unused")
    void setBci(int bci);
}

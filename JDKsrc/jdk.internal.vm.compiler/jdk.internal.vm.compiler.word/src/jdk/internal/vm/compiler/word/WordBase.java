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


















package jdk.internal.vm.compiler.word;

/**
 * The root of the interface hierarchy for machine-word-sized values.
 *
 * @since 19.0
 */
public interface WordBase {

    /**
     * Conversion to a Java primitive value.
     *
     * @since 19.0
     */
    long rawValue();

    /**
     * This is deprecated because of the easy to mistype name collision between {@link #equals} and
     * the other word based equality routines. In general you should never be statically calling
     * this method anyway.
     *
     * @since 19.0
     */
    @Override
    @Deprecated
    boolean equals(Object o);
}

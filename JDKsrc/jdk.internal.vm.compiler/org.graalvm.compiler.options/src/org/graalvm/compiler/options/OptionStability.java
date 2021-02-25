/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.options;

/**
 * Categorizes options according to their stability.
 */
public enum OptionStability {

    /**
     * A stable option is expected to remain available for many releases. End users can rely on such
     * an option being present. A stable option can still be removed but will go through a clear
     * deprecating process before being removed.
     */
    STABLE,

    /**
     * An experimental option has no guarantees of stability and might be removed at any point.
     */
    EXPERIMENTAL
}

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


package org.graalvm.compiler.nodes.spi;

import org.graalvm.compiler.nodes.gc.BarrierSet;

public interface PlatformConfigurationProvider {
    /**
     * Returns the barrier set that is used to insert the needed read/write barriers.
     */
    BarrierSet getBarrierSet();

    /**
     * Returns whether the underlying VM can recover from virtualizing large primitive unsafe writes
     * in a byte array.
     */
    boolean canVirtualizeLargeByteArrayAccess();
}

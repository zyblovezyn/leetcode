/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.memory;

import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * This interface marks nodes that access some memory location, and that have an edge to the last
 * node that kills this location.
 */
public interface MemoryAccess {

    LocationIdentity getLocationIdentity();

    /**
     *
     * @return a {@linkplain MemoryKill} that represents the last memory state in the memory graph
     *         for the {@linkplain LocationIdentity} returned by
     *         {@linkplain MemoryAccess#getLocationIdentity()}
     */
    MemoryKill getLastLocationAccess();

    /**
     * @param lla the {@link MemoryKill} that represents the last kill of the
     *            {@linkplain LocationIdentity} returned by
     *            {@linkplain MemoryAccess#getLocationIdentity()}
     */
    void setLastLocationAccess(MemoryKill lla);

}

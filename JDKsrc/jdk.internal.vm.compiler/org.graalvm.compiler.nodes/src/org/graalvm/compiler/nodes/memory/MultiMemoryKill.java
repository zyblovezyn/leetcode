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


package org.graalvm.compiler.nodes.memory;

import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * This interface marks nodes that kill multiple memory locations represented by
 * {@linkplain LocationIdentity} at once.
 */
public interface MultiMemoryKill extends MemoryKill {

    /**
     * This method is used to determine which set of memory locations is killed by this node.
     * Returning the special value {@link LocationIdentity#any()} will kill all memory locations.
     *
     * @return the identities of all locations killed by this node.
     */
    LocationIdentity[] getKilledLocationIdentities();

}

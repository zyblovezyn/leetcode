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
 * This interface marks nodes that kill a single memory location represented by
 * {@linkplain LocationIdentity}.
 */
public interface SingleMemoryKill extends MemoryKill {

    /**
     * This method is used to determine which memory location is killed by this node. Returning the
     * special value {@link LocationIdentity#any()} will kill all memory locations.
     *
     * @return the identity of the location killed by this node.
     */
    LocationIdentity getKilledLocationIdentity();

}

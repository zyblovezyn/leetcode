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

import org.graalvm.compiler.nodes.memory.address.AddressNode;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 *
 * A special form of {@linkplain MemoryAccess} exposing the {@linkplain AddressNode} representing
 * the {@linkplain LocationIdentity} touched by this memory access. Typically used during a later
 * stage in the compilation pipeline.
 */
public interface AddressableMemoryAccess extends MemoryAccess {

    /**
     * Determines if the memory touch operation represented by this node can use OS level semantics
     * for representing the null check of the memory location with an operating system level trap.
     */
    boolean canNullCheck();

    AddressNode getAddress();

    void setAddress(AddressNode address);
}

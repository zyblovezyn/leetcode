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

import org.graalvm.compiler.nodes.FixedNode;
import org.graalvm.compiler.nodes.ValueNodeInterface;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * This interface marks nodes that kill a set of memory locations represented by
 * {@linkplain LocationIdentity} (i.e. change a value at one or more locations that belong to these
 * location identities). This does not only include real memory kills like subclasses of
 * {@linkplain FixedNode} that, e.g., write a memory location, but also conceptual memory kills,
 * i.e., nodes in the memory graph that mark the last accesses to such a location, like a
 * {@linkplain MemoryPhiNode} node.
 */
public interface MemoryKill extends ValueNodeInterface {

}

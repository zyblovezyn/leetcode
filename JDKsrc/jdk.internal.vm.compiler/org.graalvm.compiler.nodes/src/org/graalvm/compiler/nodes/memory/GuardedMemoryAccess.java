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

import org.graalvm.compiler.nodes.extended.GuardedNode;
import org.graalvm.compiler.nodes.extended.GuardingNode;

/**
 *
 * A {@linkplain MemoryAccess} that requires a {@linkplain GuardingNode} as a pre-condition to its
 * execution.
 */
public interface GuardedMemoryAccess extends MemoryAccess, GuardedNode {

}

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


package org.graalvm.compiler.nodes.spi;

import org.graalvm.compiler.nodes.memory.MemoryKill;
import jdk.internal.vm.compiler.word.LocationIdentity;

public interface MemoryEdgeProxy extends Proxy, MemoryKill {

    LocationIdentity getLocationIdentity();

    MemoryKill getOriginalMemoryNode();
}

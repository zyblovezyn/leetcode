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


package org.graalvm.compiler.nodes;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeCycles;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodeinfo.NodeSize;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * Allows to build control flow structures that are syntactically correct (can be processed by all
 * Graal phases) but known to be unreachable, i.e., known to be removed at a later point in the
 * compilation pipeline. Useful together with {@link UnreachableControlSinkNode}.
 */
@NodeInfo(cycles = NodeCycles.CYCLES_IGNORED, size = NodeSize.SIZE_IGNORED)
public final class UnreachableBeginNode extends AbstractBeginNode implements SingleMemoryKill {

    public static final NodeClass<UnreachableBeginNode> TYPE = NodeClass.create(UnreachableBeginNode.class);

    public UnreachableBeginNode() {
        super(TYPE);
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return LocationIdentity.any();
    }
}

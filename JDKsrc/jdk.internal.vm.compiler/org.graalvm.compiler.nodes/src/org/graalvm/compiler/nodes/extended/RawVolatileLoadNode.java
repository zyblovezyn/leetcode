/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2020, Red Hat Inc. All rights reserved.
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



package org.graalvm.compiler.nodes.extended;

import jdk.vm.ci.meta.JavaKind;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import jdk.internal.vm.compiler.word.LocationIdentity;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_1;

@NodeInfo(cycles = CYCLES_2, size = SIZE_1)
public class RawVolatileLoadNode extends RawLoadNode implements SingleMemoryKill {
    public static final NodeClass<RawVolatileLoadNode> TYPE = NodeClass.create(RawVolatileLoadNode.class);

    public RawVolatileLoadNode(ValueNode object, ValueNode offset, JavaKind accessKind, LocationIdentity locationIdentity) {
        super(TYPE, object, offset, accessKind, locationIdentity);
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return LocationIdentity.any();
    }

    @Override
    public boolean isVolatile() {
        return true;
    }
}

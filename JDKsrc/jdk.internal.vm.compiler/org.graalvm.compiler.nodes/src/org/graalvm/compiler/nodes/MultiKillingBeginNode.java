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

import static org.graalvm.compiler.nodeinfo.InputType.Memory;
import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_0;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_0;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.memory.MultiMemoryKill;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * A begin node that kills multiple memory locations. See {@link KillingBeginNode} for a version
 * with a single killed location.
 */
@NodeInfo(allowedUsageTypes = {Memory}, cycles = CYCLES_0, size = SIZE_0)
public final class MultiKillingBeginNode extends AbstractBeginNode implements MultiMemoryKill {

    public static final NodeClass<MultiKillingBeginNode> TYPE = NodeClass.create(MultiKillingBeginNode.class);
    protected LocationIdentity[] locationIdentities;

    public MultiKillingBeginNode(LocationIdentity[] locationIdentities) {
        super(TYPE);
        this.locationIdentities = locationIdentities;
    }

    public static AbstractBeginNode begin(FixedNode with, LocationIdentity[] locationIdentities) {
        if (with instanceof MultiKillingBeginNode) {
            return (MultiKillingBeginNode) with;
        }
        AbstractBeginNode begin = with.graph().add(MultiKillingBeginNode.create(locationIdentities));
        begin.setNext(with);
        return begin;
    }

    public static AbstractBeginNode create(LocationIdentity[] locationIdentities) {
        return new MultiKillingBeginNode(locationIdentities);
    }

    @Override
    public LocationIdentity[] getKilledLocationIdentities() {
        return locationIdentities;
    }
}

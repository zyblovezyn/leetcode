/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.memory.MemoryKill;
import org.graalvm.compiler.nodes.memory.MemoryPhiNode;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import jdk.internal.vm.compiler.word.LocationIdentity;

@NodeInfo(allowedUsageTypes = {InputType.Memory}, nameTemplate = "MemoryProxy({i#value})")
public final class MemoryProxyNode extends ProxyNode implements SingleMemoryKill {

    public static final NodeClass<MemoryProxyNode> TYPE = NodeClass.create(MemoryProxyNode.class);
    @OptionalInput(InputType.Memory) MemoryKill value;
    protected final LocationIdentity locationIdentity;

    public MemoryProxyNode(MemoryKill value, LoopExitNode proxyPoint, LocationIdentity locationIdentity) {
        super(TYPE, StampFactory.forVoid(), proxyPoint);
        this.value = value;
        this.locationIdentity = locationIdentity;
    }

    public void setValue(MemoryKill newValue) {
        this.updateUsages(value.asNode(), newValue.asNode());
        this.value = newValue;
    }

    @Override
    public ValueNode value() {
        return (value == null ? null : value.asNode());
    }

    @Override
    public PhiNode createPhi(AbstractMergeNode merge) {
        return graph().addWithoutUnique(new MemoryPhiNode(merge, locationIdentity));
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return locationIdentity;
    }
}

/*
 * Copyright (c) 2017, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.nodes.aot;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_4;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_16;

import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.DeoptimizingFixedWithNextNode;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.spi.Lowerable;
import jdk.internal.vm.compiler.word.LocationIdentity;

@NodeInfo(cycles = CYCLES_4, size = SIZE_16, allowedUsageTypes = {InputType.Memory})
public class ResolveDynamicConstantNode extends DeoptimizingFixedWithNextNode implements Lowerable, SingleMemoryKill {
    public static final NodeClass<ResolveDynamicConstantNode> TYPE = NodeClass.create(ResolveDynamicConstantNode.class);

    @Input ValueNode value;

    public ResolveDynamicConstantNode(Stamp valueStamp, ValueNode value) {
        super(TYPE, valueStamp);
        this.value = value;
    }

    public ValueNode value() {
        return value;
    }

    @Override
    public boolean canDeoptimize() {
        return true;
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return LocationIdentity.any();
    }

}

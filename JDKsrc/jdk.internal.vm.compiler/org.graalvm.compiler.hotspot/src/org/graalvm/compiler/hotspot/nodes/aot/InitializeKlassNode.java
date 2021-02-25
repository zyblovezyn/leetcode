/*
 * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.
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

import static org.graalvm.compiler.nodeinfo.InputType.Memory;
import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_4;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_16;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.DeoptimizingFixedWithNextNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.spi.Lowerable;
import jdk.internal.vm.compiler.word.LocationIdentity;

@NodeInfo(cycles = CYCLES_4, size = SIZE_16, allowedUsageTypes = {Memory})
public class InitializeKlassNode extends DeoptimizingFixedWithNextNode implements Lowerable, SingleMemoryKill {
    public static final NodeClass<InitializeKlassNode> TYPE = NodeClass.create(InitializeKlassNode.class);

    @Input ValueNode value;

    public InitializeKlassNode(ValueNode value) {
        super(TYPE, value.stamp(NodeView.DEFAULT));
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

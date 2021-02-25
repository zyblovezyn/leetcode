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


package org.graalvm.compiler.nodes.debug;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeCycles;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodeinfo.NodeSize;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.AbstractMemoryCheckpoint;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.spi.LIRLowerable;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * Debug node that can be used when an arbitrary side-effect and when a
 * {@link LocationIdentity#ANY_LOCATION} kill is needed.
 */
@NodeInfo(cycles = NodeCycles.CYCLES_IGNORED, size = NodeSize.SIZE_IGNORED, allowedUsageTypes = {InputType.Memory})
public class SideEffectNode extends AbstractMemoryCheckpoint implements LIRLowerable, SingleMemoryKill {
    public static final NodeClass<SideEffectNode> TYPE = NodeClass.create(SideEffectNode.class);

    @OptionalInput ValueNode value;

    public SideEffectNode() {
        super(TYPE, StampFactory.forVoid());
    }

    public SideEffectNode(ValueNode value) {
        super(TYPE, value.stamp(NodeView.DEFAULT));
        this.value = value;
    }

    @Override
    public boolean hasSideEffect() {
        return true;
    }

    @Override
    public void generate(NodeLIRBuilderTool generator) {
        if (value != null) {
            generator.setResult(this, generator.operand(value));
        }
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return LocationIdentity.any();
    }

}

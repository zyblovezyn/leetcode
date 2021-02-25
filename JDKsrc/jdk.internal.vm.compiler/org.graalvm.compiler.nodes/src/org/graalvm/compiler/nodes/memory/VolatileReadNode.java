/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Red Hat Inc. All rights reserved.
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

import static org.graalvm.compiler.nodeinfo.InputType.Memory;
import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_1;

import org.graalvm.compiler.core.common.LIRKind;
import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.memory.address.AddressNode;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;
import jdk.internal.vm.compiler.word.LocationIdentity;

@NodeInfo(nameTemplate = "VolatileRead#{p#location/s}", allowedUsageTypes = Memory, cycles = CYCLES_2, size = SIZE_1)
public class VolatileReadNode extends ReadNode implements SingleMemoryKill, Lowerable {
    public static final NodeClass<VolatileReadNode> TYPE = NodeClass.create(VolatileReadNode.class);

    public VolatileReadNode(AddressNode address, Stamp stamp, BarrierType barrierType) {
        super(TYPE, address, LocationIdentity.any(), stamp, null, barrierType, false, null);
    }

    @Override
    public void generate(NodeLIRBuilderTool gen) {
        LIRKind readKind = gen.getLIRGeneratorTool().getLIRKind(getAccessStamp(NodeView.DEFAULT));
        gen.setResult(this, gen.getLIRGeneratorTool().getArithmetic().emitVolatileLoad(readKind, gen.operand(address), gen.state(this)));
    }

    @SuppressWarnings("try")
    @Override
    public FloatingAccessNode asFloatingNode() {
        throw new RuntimeException();
    }

    @Override
    public boolean canFloat() {
        return false;
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return LocationIdentity.any();
    }

    @Override
    public boolean canNullCheck() {
        return false;
    }

}

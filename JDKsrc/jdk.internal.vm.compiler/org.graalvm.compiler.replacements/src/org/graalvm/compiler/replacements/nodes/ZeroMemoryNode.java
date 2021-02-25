/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.nodes;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_8;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_8;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.FixedAccessNode;
import org.graalvm.compiler.nodes.memory.address.AddressNode;
import org.graalvm.compiler.nodes.memory.address.OffsetAddressNode;
import org.graalvm.compiler.nodes.spi.LIRLowerable;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * Zeros a chunk of memory.
 */
@NodeInfo(nameTemplate = "ZeroMemory#{p#location/s}", allowedUsageTypes = {InputType.Memory}, cycles = CYCLES_8, size = SIZE_8)
public class ZeroMemoryNode extends FixedAccessNode implements LIRLowerable {
    public static final NodeClass<ZeroMemoryNode> TYPE = NodeClass.create(ZeroMemoryNode.class);

    @Input ValueNode length;
    private final boolean isAligned;

    public ZeroMemoryNode(ValueNode address, ValueNode length, boolean isAligned, LocationIdentity locationIdentity) {
        this(OffsetAddressNode.create(address), length, isAligned, locationIdentity, BarrierType.NONE);
    }

    public ZeroMemoryNode(AddressNode address, ValueNode length, boolean isAligned, LocationIdentity locationIdentity, BarrierType type) {
        super(TYPE, address, locationIdentity, StampFactory.forVoid(), type);
        this.length = length;
        this.isAligned = isAligned;
    }

    @Override
    public void generate(NodeLIRBuilderTool gen) {
        gen.getLIRGeneratorTool().emitZeroMemory(gen.operand(getAddress()), gen.operand(length), isAligned);
    }

    @Override
    public boolean canNullCheck() {
        return false;
    }

    @NodeIntrinsic
    public static native void zero(Word address, long length, @ConstantNodeParameter boolean isAligned, @ConstantNodeParameter LocationIdentity locationIdentity);
}

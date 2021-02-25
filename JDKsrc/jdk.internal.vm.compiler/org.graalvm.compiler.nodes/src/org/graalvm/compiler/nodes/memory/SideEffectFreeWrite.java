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


package org.graalvm.compiler.nodes.memory;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.FrameState;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.address.AddressNode;
import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * This is a special form of write node that does not have a side effect to the interpreter, i.e.,
 * it does not modify memory that is visible to other threads or modifies state beyond what is
 * captured in {@link FrameState} nodes. Thus is should only be used with caution in suitable
 * scenarios.
 */
@NodeInfo(nameTemplate = "SideEffectFreeWrite#{p#location/s}")
public class SideEffectFreeWrite extends WriteNode {

    public static final NodeClass<SideEffectFreeWrite> TYPE = NodeClass.create(SideEffectFreeWrite.class);

    public SideEffectFreeWrite(AddressNode address, LocationIdentity location, ValueNode value, BarrierType barrierType) {
        super(TYPE, address, location, value, barrierType);
    }

    public static WriteNode createWithoutSideEffect(AddressNode address, LocationIdentity location, ValueNode value) {
        return new SideEffectFreeWrite(address, location, value, BarrierType.NONE);
    }

    @Override
    public boolean hasSideEffect() {
        return false;
    }
}

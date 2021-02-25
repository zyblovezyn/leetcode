/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.gc;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.address.AddressNode;
import org.graalvm.compiler.nodes.spi.Lowerable;

@NodeInfo
public abstract class ArrayRangeWriteBarrier extends WriteBarrier implements Lowerable {

    public static final NodeClass<ArrayRangeWriteBarrier> TYPE = NodeClass.create(ArrayRangeWriteBarrier.class);
    @Input ValueNode length;

    private final int elementStride;

    protected ArrayRangeWriteBarrier(NodeClass<? extends ArrayRangeWriteBarrier> c, AddressNode address, ValueNode length, int elementStride) {
        super(c, address);
        this.length = length;
        this.elementStride = elementStride;
    }

    public ValueNode getLength() {
        return length;
    }

    public int getElementStride() {
        return elementStride;
    }
}

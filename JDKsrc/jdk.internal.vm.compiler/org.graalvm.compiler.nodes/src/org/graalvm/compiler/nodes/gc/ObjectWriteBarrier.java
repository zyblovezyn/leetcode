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

@NodeInfo
public abstract class ObjectWriteBarrier extends WriteBarrier {

    public static final NodeClass<ObjectWriteBarrier> TYPE = NodeClass.create(ObjectWriteBarrier.class);
    @OptionalInput protected ValueNode value;
    protected final boolean precise;

    protected ObjectWriteBarrier(NodeClass<? extends ObjectWriteBarrier> c, AddressNode address, ValueNode value, boolean precise) {
        super(c, address);
        this.value = value;
        this.precise = precise;
    }

    public ValueNode getValue() {
        return value;
    }

    public boolean usePrecise() {
        return precise;
    }
}

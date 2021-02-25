/*
 * Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.memory.address;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.spi.Lowerable;

import jdk.vm.ci.meta.JavaKind;

/**
 * Represents an address that points to an element of a Java array.
 */
@NodeInfo(allowedUsageTypes = InputType.Association)
public class IndexAddressNode extends AddressNode implements Lowerable {
    public static final NodeClass<IndexAddressNode> TYPE = NodeClass.create(IndexAddressNode.class);

    @Input ValueNode array;
    @Input ValueNode index;

    private final JavaKind arrayKind;
    private final JavaKind elementKind;

    public IndexAddressNode(ValueNode array, ValueNode index, JavaKind elementKind) {
        this(array, index, elementKind, elementKind);
    }

    public IndexAddressNode(ValueNode array, ValueNode index, JavaKind arrayKind, JavaKind elementKind) {
        super(TYPE);
        this.array = array;
        this.index = index;
        this.arrayKind = arrayKind;
        this.elementKind = elementKind;
    }

    @Override
    public ValueNode getBase() {
        return array;
    }

    public ValueNode getArray() {
        return array;
    }

    @Override
    public ValueNode getIndex() {
        return index;
    }

    @Override
    public long getMaxConstantDisplacement() {
        return Long.MAX_VALUE;
    }

    public JavaKind getArrayKind() {
        return arrayKind;
    }

    public JavaKind getElementKind() {
        return elementKind;
    }

}

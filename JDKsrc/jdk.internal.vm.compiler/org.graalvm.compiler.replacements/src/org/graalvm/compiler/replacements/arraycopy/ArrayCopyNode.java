/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.arraycopy;

import static jdk.internal.vm.compiler.word.LocationIdentity.any;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.NamedLocationIdentity;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.replacements.nodes.BasicArrayCopyNode;
import jdk.internal.vm.compiler.word.LocationIdentity;

@NodeInfo
public final class ArrayCopyNode extends BasicArrayCopyNode implements Lowerable {

    public static final NodeClass<ArrayCopyNode> TYPE = NodeClass.create(ArrayCopyNode.class);

    protected final boolean forceAnyLocation;

    public ArrayCopyNode(int bci, ValueNode src, ValueNode srcPos, ValueNode dst, ValueNode dstPos, ValueNode length) {
        this(bci, src, srcPos, dst, dstPos, length, false);
    }

    public ArrayCopyNode(int bci, ValueNode src, ValueNode srcPos, ValueNode dst, ValueNode dstPos, ValueNode length, boolean forceAnyLocation) {
        super(TYPE, src, srcPos, dst, dstPos, length, null, bci);
        this.forceAnyLocation = forceAnyLocation;
        if (!forceAnyLocation) {
            elementKind = ArrayCopy.selectComponentKind(this);
        } else {
            assert elementKind == null;
        }
    }

    public ArrayCopyNode(ArrayCopy arraycopy) {
        this(arraycopy.getBci(), arraycopy.getSource(), arraycopy.getSourcePosition(), arraycopy.getDestination(), arraycopy.getDestinationPosition(), arraycopy.getLength());
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        if (!forceAnyLocation && elementKind == null) {
            elementKind = ArrayCopy.selectComponentKind(this);
        }
        if (elementKind != null) {
            return NamedLocationIdentity.getArrayLocation(elementKind);
        }
        return any();
    }

    public boolean killsAnyLocation() {
        return forceAnyLocation;
    }
}

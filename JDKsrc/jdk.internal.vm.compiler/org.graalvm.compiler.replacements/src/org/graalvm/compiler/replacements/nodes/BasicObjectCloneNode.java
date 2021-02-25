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


package org.graalvm.compiler.replacements.nodes;

import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_8;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeCycles;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ValueNode;

@NodeInfo(cycles = NodeCycles.CYCLES_UNKNOWN, size = SIZE_8)
public abstract class BasicObjectCloneNode extends MacroStateSplitNode implements ObjectClone {

    public static final NodeClass<BasicObjectCloneNode> TYPE = NodeClass.create(BasicObjectCloneNode.class);

    public BasicObjectCloneNode(NodeClass<? extends MacroNode> c, MacroParams p) {
        super(c, p);
        updateStamp(computeStamp(getObject()));
    }

    @Override
    public boolean inferStamp() {
        return updateStamp(stamp.improveWith(computeStamp(getObject())));
    }

    @Override
    public ValueNode getObject() {
        return arguments.get(0);
    }
}

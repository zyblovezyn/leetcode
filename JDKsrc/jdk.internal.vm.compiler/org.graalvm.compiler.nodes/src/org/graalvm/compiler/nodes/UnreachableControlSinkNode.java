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


package org.graalvm.compiler.nodes;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeCycles;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodeinfo.NodeSize;

/**
 * See also {@link org.graalvm.compiler.nodes.UnreachableBeginNode}.
 */
@NodeInfo(cycles = NodeCycles.CYCLES_IGNORED, size = NodeSize.SIZE_IGNORED)
public final class UnreachableControlSinkNode extends ControlSinkNode {

    public static final NodeClass<UnreachableControlSinkNode> TYPE = NodeClass.create(UnreachableControlSinkNode.class);

    public UnreachableControlSinkNode() {
        super(TYPE, StampFactory.forVoid());
    }
}

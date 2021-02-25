/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Arm Limited. All rights reserved.
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


package org.graalvm.compiler.replacements.aarch64;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_4;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_4;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.replacements.nodes.BitCountNode;

@NodeInfo(cycles = CYCLES_4, size = SIZE_4)
public final class AArch64BitCountNode extends BitCountNode {

    public static final NodeClass<AArch64BitCountNode> TYPE = NodeClass.create(AArch64BitCountNode.class);

    public AArch64BitCountNode(ValueNode value) {
        super(TYPE, value);
    }
}

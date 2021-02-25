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


package org.graalvm.compiler.replacements;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_256;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_64;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.spi.LoweringTool;
import org.graalvm.compiler.replacements.nodes.MacroNode;
import org.graalvm.compiler.replacements.nodes.MacroStateSplitNode;

@NodeInfo(size = SIZE_64, cycles = CYCLES_256)
public class StringUTF16IndexOfNode extends MacroStateSplitNode {
    public static final NodeClass<StringUTF16IndexOfNode> TYPE = NodeClass.create(StringUTF16IndexOfNode.class);

    public StringUTF16IndexOfNode(MacroParams p) {
        super(TYPE, p);
    }

    /**
     * Even though this implementation is the same as {@link Lowerable#lower}, it is required
     * because we would actually inherit {@link MacroNode#lower} which we do not want.
     */
    @Override
    public void lower(LoweringTool tool) {
        tool.getLowerer().lower(this, tool);
    }
}

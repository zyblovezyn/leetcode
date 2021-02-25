/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.nodes;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_4;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_16;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.DeoptimizingFixedWithNextNode;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.spi.Lowerable;

@NodeInfo(cycles = CYCLES_4, size = SIZE_16)
public class KlassBeingInitializedCheckNode extends DeoptimizingFixedWithNextNode implements Lowerable {
    public static final NodeClass<KlassBeingInitializedCheckNode> TYPE = NodeClass.create(KlassBeingInitializedCheckNode.class);

    @Input protected ValueNode klass;

    public KlassBeingInitializedCheckNode(ValueNode klass) {
        super(TYPE, StampFactory.forVoid());
        this.klass = klass;
    }

    public ValueNode getKlass() {
        return klass;
    }

    @Override
    public boolean canDeoptimize() {
        return true;
    }

}

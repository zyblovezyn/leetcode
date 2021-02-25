/*
 * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.replacements;

import static org.graalvm.compiler.core.common.GraalOptions.ImmutableCode;
import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_0;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_0;

import org.graalvm.compiler.core.common.type.AbstractObjectStamp;
import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.Canonicalizable;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.AbstractStateSplit;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.DeoptBciSupplier;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.spi.Lowerable;
import jdk.internal.vm.compiler.word.LocationIdentity;

import jdk.vm.ci.hotspot.HotSpotObjectConstant;
import jdk.vm.ci.meta.JavaConstant;

@NodeInfo(cycles = CYCLES_0, size = SIZE_0)
public class IdentityHashCodeNode extends AbstractStateSplit implements Canonicalizable, Lowerable, SingleMemoryKill, DeoptBciSupplier {

    public static final NodeClass<IdentityHashCodeNode> TYPE = NodeClass.create(IdentityHashCodeNode.class);

    @Input ValueNode object;
    private int bci;

    public IdentityHashCodeNode(ValueNode object, int bci) {
        super(TYPE, StampFactory.forInteger(32));
        this.object = object;
        this.bci = bci;
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return HotSpotReplacementsUtil.MARK_WORD_LOCATION;
    }

    public ValueNode object() {
        return object;
    }

    @Override
    public int bci() {
        return bci;
    }

    @Override
    public void setBci(int bci) {
        this.bci = bci;
    }

    @Override
    public Node canonical(CanonicalizerTool tool) {
        if (object.isConstant()) {
            assert object.stamp(NodeView.DEFAULT) instanceof AbstractObjectStamp;
            JavaConstant c = (JavaConstant) object.asConstant();
            if (ImmutableCode.getValue(tool.getOptions())) {
                return this;
            }
            JavaConstant identityHashCode = null;
            if (c.isNull()) {
                identityHashCode = JavaConstant.forInt(0);
            } else {
                identityHashCode = JavaConstant.forInt(((HotSpotObjectConstant) c).getIdentityHashCode());
            }

            return new ConstantNode(identityHashCode, StampFactory.forConstant(identityHashCode));
        }
        return this;
    }
}

/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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



package org.graalvm.compiler.replacements.nodes.arithmetic;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_2;

import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.graph.IterableNodeType;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.Canonicalizable.BinaryCommutative;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.graph.spi.Simplifiable;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.AbstractBeginNode;
import org.graalvm.compiler.nodes.LogicConstantNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.calc.BinaryNode;

import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;

@NodeInfo(cycles = CYCLES_2, size = SIZE_2)
public final class IntegerAddExactOverflowNode extends IntegerExactOverflowNode implements Simplifiable, BinaryCommutative<ValueNode>, IterableNodeType {
    public static final NodeClass<IntegerAddExactOverflowNode> TYPE = NodeClass.create(IntegerAddExactOverflowNode.class);

    public IntegerAddExactOverflowNode(ValueNode x, ValueNode y) {
        super(TYPE, x, y);
    }

    @Override
    public ValueNode canonical(CanonicalizerTool tool, ValueNode forX, ValueNode forY) {
        if (forX.isConstant() && !forY.isConstant()) {
            return new IntegerAddExactOverflowNode(forY, forX).canonical(tool);
        }
        if (forX.isConstant() && forY.isConstant()) {
            return canonicalXYconstant(forX, forY);
        } else if (forY.isConstant()) {
            long c = forY.asJavaConstant().asLong();
            if (c == 0) {
                return LogicConstantNode.forBoolean(false);
            }
        }
        if (!IntegerStamp.addCanOverflow((IntegerStamp) forX.stamp(NodeView.DEFAULT), (IntegerStamp) forY.stamp(NodeView.DEFAULT))) {
            return LogicConstantNode.forBoolean(false);
        }
        return this;
    }

    private static LogicConstantNode canonicalXYconstant(ValueNode forX, ValueNode forY) {
        JavaConstant xConst = forX.asJavaConstant();
        JavaConstant yConst = forY.asJavaConstant();
        assert xConst.getJavaKind() == yConst.getJavaKind();
        try {
            if (xConst.getJavaKind() == JavaKind.Int) {
                Math.addExact(xConst.asInt(), yConst.asInt());
            } else {
                assert xConst.getJavaKind() == JavaKind.Long;
                Math.addExact(xConst.asLong(), yConst.asLong());
            }
        } catch (ArithmeticException ex) {
            // Always overflows
            return LogicConstantNode.forBoolean(true);
        }
        // Never overflows
        return LogicConstantNode.forBoolean(false);
    }

    @Override
    protected IntegerExactArithmeticSplitNode createSplit(Stamp splitStamp, AbstractBeginNode next, AbstractBeginNode overflow) {
        return new IntegerAddExactSplitNode(splitStamp, x, y, next, overflow);
    }

    @Override
    protected Class<? extends BinaryNode> getCoupledType() {
        return IntegerAddExactNode.class;
    }
}

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

import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;
import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.graph.spi.Simplifiable;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.AbstractBeginNode;
import org.graalvm.compiler.nodes.LogicConstantNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.calc.BinaryNode;
import org.graalvm.compiler.nodes.util.GraphUtil;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_2;

@NodeInfo(cycles = CYCLES_2, size = SIZE_2)
public class IntegerSubExactOverflowNode extends IntegerExactOverflowNode implements Simplifiable {
    public static final NodeClass<IntegerSubExactOverflowNode> TYPE = NodeClass.create(IntegerSubExactOverflowNode.class);

    public IntegerSubExactOverflowNode(ValueNode x, ValueNode y) {
        super(TYPE, x, y);
    }

    @Override
    public ValueNode canonical(CanonicalizerTool tool, ValueNode forX, ValueNode forY) {
        if (GraphUtil.unproxify(forX) == GraphUtil.unproxify(forY)) {
            return LogicConstantNode.forBoolean(false);
        }
        if (forX.isConstant() && forY.isConstant()) {
            return canonicalXYconstant(forX, forY);
        } else if (forY.isConstant()) {
            long c = forY.asJavaConstant().asLong();
            if (c == 0) {
                return LogicConstantNode.forBoolean(false);
            }
        }
        if (!IntegerStamp.subtractionCanOverflow((IntegerStamp) x.stamp(NodeView.DEFAULT), (IntegerStamp) y.stamp(NodeView.DEFAULT))) {
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
                Math.subtractExact(xConst.asInt(), yConst.asInt());
            } else {
                assert xConst.getJavaKind() == JavaKind.Long;
                Math.subtractExact(xConst.asLong(), yConst.asLong());
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
        return new IntegerSubExactSplitNode(splitStamp, x, y, next, overflow);
    }

    @Override
    protected Class<? extends BinaryNode> getCoupledType() {
        return IntegerSubExactNode.class;
    }

}

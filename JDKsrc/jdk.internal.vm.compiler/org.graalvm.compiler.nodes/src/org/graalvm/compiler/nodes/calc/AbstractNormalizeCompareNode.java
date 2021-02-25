/*
 * Copyright (c) 2009, 2018, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.calc;

import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_2;

import org.graalvm.compiler.core.common.calc.CanonicalCondition;
import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.IterableNodeType;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeCycles;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.LogicConstantNode;
import org.graalvm.compiler.nodes.LogicNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.options.OptionValues;

import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.MetaAccessProvider;

/**
 * Returns -1, 0, or 1 if either x &lt; y, x == y, or x &gt; y.
 */
@NodeInfo(cycles = NodeCycles.CYCLES_2, size = SIZE_2)
public abstract class AbstractNormalizeCompareNode extends BinaryNode implements IterableNodeType {
    public static final NodeClass<AbstractNormalizeCompareNode> TYPE = NodeClass.create(AbstractNormalizeCompareNode.class);

    public AbstractNormalizeCompareNode(NodeClass<? extends BinaryNode> c, JavaKind kind, ValueNode x, ValueNode y) {
        super(c, StampFactory.forInteger(kind, -1, 1), x, y);
    }

    @Override
    public boolean inferStamp() {
        return false;
    }

    @Override
    public Stamp foldStamp(Stamp stampX, Stamp stampY) {
        return stamp(NodeView.DEFAULT);
    }

    protected static ValueNode tryConstantFold(ValueNode x, ValueNode y, boolean isUnorderedLess, boolean unsigned, JavaKind kind, ConstantReflectionProvider constantReflection) {
        LogicNode result = CompareNode.tryConstantFold(CanonicalCondition.EQ, x, y, null, false);
        if (result instanceof LogicConstantNode) {
            LogicConstantNode logicConstantNode = (LogicConstantNode) result;
            LogicNode resultLT = CompareNode.tryConstantFold(unsigned ? CanonicalCondition.BT : CanonicalCondition.LT, x, y, constantReflection, isUnorderedLess);
            if (resultLT instanceof LogicConstantNode) {
                LogicConstantNode logicConstantNodeLT = (LogicConstantNode) resultLT;
                if (logicConstantNodeLT.getValue()) {
                    return ConstantNode.forIntegerKind(kind, -1);
                } else if (logicConstantNode.getValue()) {
                    return ConstantNode.forIntegerKind(kind, 0);
                } else {
                    return ConstantNode.forIntegerKind(kind, 1);
                }
            }
        }
        return null;
    }

    public abstract LogicNode createEqualComparison();

    public abstract LogicNode createEqualComparison(ConstantReflectionProvider constantReflection, MetaAccessProvider metaAccess, OptionValues options, Integer smallestCompareWidth, NodeView view);

    public LogicNode createLowerComparison() {
        return createLowerComparison(false);
    }

    public LogicNode createLowerComparison(ConstantReflectionProvider constantReflection, MetaAccessProvider metaAccess, OptionValues options, Integer smallestCompareWidth, NodeView view) {
        return createLowerComparison(false, constantReflection, metaAccess, options, smallestCompareWidth, view);
    }

    public abstract LogicNode createLowerComparison(boolean swapInputs);

    public abstract LogicNode createLowerComparison(boolean swapInputs, ConstantReflectionProvider constantReflection, MetaAccessProvider metaAccess, OptionValues options,
                    Integer smallestCompareWidth, NodeView view);
}

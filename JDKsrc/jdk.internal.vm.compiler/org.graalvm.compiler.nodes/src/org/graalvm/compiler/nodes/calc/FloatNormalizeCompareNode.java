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

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.LogicNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.options.OptionValues;

import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.MetaAccessProvider;

/**
 * If the comparison is undecided (one of the inputs is NaN), the result is 1 if isUnorderedLess is
 * false and -1 if isUnorderedLess is true.
 */
@NodeInfo
public final class FloatNormalizeCompareNode extends AbstractNormalizeCompareNode {
    public static final NodeClass<FloatNormalizeCompareNode> TYPE = NodeClass.create(FloatNormalizeCompareNode.class);
    protected final boolean isUnorderedLess;

    public FloatNormalizeCompareNode(ValueNode x, ValueNode y, JavaKind kind, boolean isUnorderedLess) {
        super(TYPE, kind, x, y);
        this.isUnorderedLess = isUnorderedLess;
    }

    public static ValueNode create(ValueNode x, ValueNode y, boolean isUnorderedLess, JavaKind kind, ConstantReflectionProvider constantReflection) {
        ValueNode result = tryConstantFold(x, y, isUnorderedLess, false, kind, constantReflection);
        if (result != null) {
            return result;
        }

        return new FloatNormalizeCompareNode(x, y, kind, isUnorderedLess);
    }

    @Override
    public ValueNode canonical(CanonicalizerTool tool, ValueNode forX, ValueNode forY) {
        NodeView view = NodeView.from(tool);
        ValueNode result = tryConstantFold(x, y, isUnorderedLess, false, stamp(view).getStackKind(), tool.getConstantReflection());
        if (result != null) {
            return result;
        }
        return this;
    }

    public boolean isUnorderedLess() {
        return isUnorderedLess;
    }

    @Override
    public LogicNode createEqualComparison() {
        return FloatEqualsNode.create(x, y, NodeView.DEFAULT);
    }

    @Override
    public LogicNode createEqualComparison(ConstantReflectionProvider constantReflection, MetaAccessProvider metaAccess, OptionValues options, Integer smallestCompareWidth, NodeView view) {
        return FloatEqualsNode.create(constantReflection, metaAccess, options, smallestCompareWidth, x, y, NodeView.DEFAULT);
    }

    @Override
    public LogicNode createLowerComparison(boolean swapInputs) {
        ValueNode a = swapInputs ? y : x;
        ValueNode b = swapInputs ? x : y;
        return FloatLessThanNode.create(a, b, isUnorderedLess() ^ swapInputs, NodeView.DEFAULT);
    }

    @Override
    public LogicNode createLowerComparison(boolean swapInputs, ConstantReflectionProvider constantReflection, MetaAccessProvider metaAccess, OptionValues options, Integer smallestCompareWidth,
                    NodeView view) {
        ValueNode a = swapInputs ? y : x;
        ValueNode b = swapInputs ? x : y;
        return FloatLessThanNode.create(constantReflection, metaAccess, options, smallestCompareWidth, a, b, isUnorderedLess() ^ swapInputs, NodeView.DEFAULT);
    }
}

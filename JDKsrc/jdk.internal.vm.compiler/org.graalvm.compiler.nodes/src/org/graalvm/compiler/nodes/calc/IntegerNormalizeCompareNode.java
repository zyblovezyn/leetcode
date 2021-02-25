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

@NodeInfo
public final class IntegerNormalizeCompareNode extends AbstractNormalizeCompareNode {
    public static final NodeClass<IntegerNormalizeCompareNode> TYPE = NodeClass.create(IntegerNormalizeCompareNode.class);
    protected final boolean unsigned;

    public IntegerNormalizeCompareNode(ValueNode x, ValueNode y, JavaKind kind, boolean unsigned) {
        super(TYPE, kind, x, y);
        this.unsigned = unsigned;
    }

    public static ValueNode create(ValueNode x, ValueNode y, boolean unsigned, JavaKind kind, ConstantReflectionProvider constantReflection) {
        ValueNode result = tryConstantFold(x, y, false, unsigned, kind, constantReflection);
        if (result != null) {
            return result;
        }

        return new IntegerNormalizeCompareNode(x, y, kind, unsigned);
    }

    @Override
    public ValueNode canonical(CanonicalizerTool tool, ValueNode forX, ValueNode forY) {
        NodeView view = NodeView.from(tool);
        ValueNode result = tryConstantFold(x, y, false, unsigned, stamp(view).getStackKind(), tool.getConstantReflection());
        if (result != null) {
            return result;
        }
        return this;
    }

    @Override
    public LogicNode createLowerComparison(boolean swapInputs) {
        ValueNode a = swapInputs ? y : x;
        ValueNode b = swapInputs ? x : y;
        if (unsigned) {
            return IntegerBelowNode.create(a, b, NodeView.DEFAULT);
        } else {
            return IntegerLessThanNode.create(a, b, NodeView.DEFAULT);
        }
    }

    @Override
    public LogicNode createLowerComparison(boolean swapInputs, ConstantReflectionProvider constantReflection, MetaAccessProvider metaAccess, OptionValues options, Integer smallestCompareWidth,
                    NodeView view) {
        ValueNode a = swapInputs ? y : x;
        ValueNode b = swapInputs ? x : y;
        if (unsigned) {
            return IntegerBelowNode.create(constantReflection, metaAccess, options, smallestCompareWidth, a, b, NodeView.DEFAULT);
        } else {
            return IntegerLessThanNode.create(constantReflection, metaAccess, options, smallestCompareWidth, a, b, NodeView.DEFAULT);
        }
    }

    @Override
    public LogicNode createEqualComparison() {
        return IntegerEqualsNode.create(x, y, NodeView.DEFAULT);
    }

    @Override
    public LogicNode createEqualComparison(ConstantReflectionProvider constantReflection, MetaAccessProvider metaAccess, OptionValues options, Integer smallestCompareWidth, NodeView view) {
        return IntegerEqualsNode.create(constantReflection, metaAccess, options, smallestCompareWidth, x, y, view);
    }
}

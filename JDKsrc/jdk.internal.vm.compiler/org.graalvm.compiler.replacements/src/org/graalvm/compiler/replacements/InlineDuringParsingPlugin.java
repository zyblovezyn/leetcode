/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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

import static org.graalvm.compiler.core.common.GraalOptions.TrivialInliningSize;
import static org.graalvm.compiler.java.BytecodeParserOptions.InlineDuringParsingMaxDepth;
import static org.graalvm.compiler.nodes.graphbuilderconf.InlineInvokePlugin.InlineInfo.createStandardInlineInfo;

import org.graalvm.compiler.java.BytecodeParserOptions;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.graphbuilderconf.GraphBuilderContext;
import org.graalvm.compiler.nodes.graphbuilderconf.InlineInvokePlugin;

import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.services.Services;

public final class InlineDuringParsingPlugin implements InlineInvokePlugin {

    private static int getInteger(String name, int def) {
        String value = Services.getSavedProperties().get(name);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return def;
    }

    /**
     * Budget which when exceeded reduces the effective value of
     * {@link BytecodeParserOptions#InlineDuringParsingMaxDepth} to
     * {@link #MaxDepthAfterBudgetExceeded}.
     */
    private static final int NodeBudget = getInteger("InlineDuringParsingPlugin.NodeBudget", 2000);

    private static final int MaxDepthAfterBudgetExceeded = getInteger("InlineDuringParsingPlugin.MaxDepthAfterBudgetExceeded", 3);

    @Override
    public InlineInfo shouldInlineInvoke(GraphBuilderContext b, ResolvedJavaMethod method, ValueNode[] args) {
        // @formatter:off
        if (method.hasBytecodes() &&
            method.getDeclaringClass().isLinked() &&
            method.canBeInlined()) {

            // Test force inlining first
            if (method.shouldBeInlined() && checkInliningDepth(b)) {
                return createStandardInlineInfo(method);
            }

            if (!method.isSynchronized() &&
                checkSize(method, args, b.getGraph()) &&
                checkInliningDepth(b)) {
                return createStandardInlineInfo(method);
            }
        }
        // @formatter:on
        return null;
    }

    private static boolean checkInliningDepth(GraphBuilderContext b) {
        int nodeCount = b.getGraph().getNodeCount();
        int maxDepth = InlineDuringParsingMaxDepth.getValue(b.getOptions());
        if (nodeCount > NodeBudget && MaxDepthAfterBudgetExceeded < maxDepth) {
            maxDepth = MaxDepthAfterBudgetExceeded;
        }
        return b.getDepth() < maxDepth;
    }

    private static boolean checkSize(ResolvedJavaMethod method, ValueNode[] args, StructuredGraph graph) {
        int bonus = 1;
        for (ValueNode v : args) {
            if (v.isConstant()) {
                bonus++;
            }
        }
        return method.getCode().length <= TrivialInliningSize.getValue(graph.getOptions()) * bonus;
    }
}

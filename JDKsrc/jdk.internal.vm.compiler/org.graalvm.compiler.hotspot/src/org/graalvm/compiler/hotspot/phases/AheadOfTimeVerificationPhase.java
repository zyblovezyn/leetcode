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


package org.graalvm.compiler.hotspot.phases;

import static org.graalvm.compiler.nodes.ConstantNode.getConstantNodes;

import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.nodes.type.StampTool;
import org.graalvm.compiler.phases.VerifyPhase;

import jdk.vm.ci.hotspot.HotSpotObjectConstant;
import jdk.vm.ci.meta.JavaKind;

/**
 * Checks for {@link #isIllegalObjectConstant(ConstantNode) illegal} object constants in a graph
 * processed for AOT compilation.
 *
 * @see LoadJavaMirrorWithKlassPhase
 */
public class AheadOfTimeVerificationPhase extends VerifyPhase<CoreProviders> {

    @Override
    protected void verify(StructuredGraph graph, CoreProviders context) {
        for (ConstantNode node : getConstantNodes(graph)) {
            if (isIllegalObjectConstant(node)) {
                throw new VerificationError("illegal object constant: " + node);
            }
        }
    }

    public static boolean isIllegalObjectConstant(ConstantNode node) {
        return isObject(node) &&
                        !isNullReference(node) &&
                        !isInternedString(node) &&
                        !isDirectMethodHandle(node) &&
                        !isBoundMethodHandle(node) &&
                        !isVarHandle(node);
    }

    private static boolean isObject(ConstantNode node) {
        return node.getStackKind() == JavaKind.Object;
    }

    private static boolean isNullReference(ConstantNode node) {
        return isObject(node) && node.isNullConstant();
    }

    private static boolean isDirectMethodHandle(ConstantNode node) {
        String typeName = StampTool.typeOrNull(node).getName();
        if (!isObject(node)) {
            return false;
        }

        switch (typeName) {
            case "Ljava/lang/invoke/DirectMethodHandle;":
            case "Ljava/lang/invoke/DirectMethodHandle$StaticAccessor;":
            case "Ljava/lang/invoke/DirectMethodHandle$Accessor;":
            case "Ljava/lang/invoke/DirectMethodHandle$Constructor;":
            case "Ljava/lang/invoke/DirectMethodHandle$Special;":
            case "Ljava/lang/invoke/DirectMethodHandle$Interface;":
                return true;
            default:
                return false;
        }
    }

    private static boolean isBoundMethodHandle(ConstantNode node) {
        if (!isObject(node)) {
            return false;
        }
        return StampTool.typeOrNull(node).getName().startsWith("Ljava/lang/invoke/BoundMethodHandle");
    }

    private static boolean isVarHandle(ConstantNode node) {
        if (!isObject(node)) {
            return false;
        }
        String name = StampTool.typeOrNull(node).getName();
        return name.equals("Ljava/lang/invoke/VarHandle$AccessDescriptor;");
    }

    private static boolean isInternedString(ConstantNode node) {
        if (!isObject(node)) {
            return false;
        }

        HotSpotObjectConstant c = (HotSpotObjectConstant) node.asConstant();
        return c.isInternedString();
    }
}

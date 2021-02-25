/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.nodes;

import org.graalvm.compiler.debug.GraalError;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.Canonicalizable;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.FrameState;
import org.graalvm.compiler.nodes.InvokeNode;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.spi.LoweringTool;

import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.MetaAccessProvider;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.ResolvedJavaType;

@NodeInfo
public abstract class ReflectionGetCallerClassNode extends MacroStateSplitNode implements Canonicalizable, Lowerable {

    public static final NodeClass<ReflectionGetCallerClassNode> TYPE = NodeClass.create(ReflectionGetCallerClassNode.class);

    protected ReflectionGetCallerClassNode(NodeClass<? extends ReflectionGetCallerClassNode> c, MacroParams p) {
        super(c, p);
    }

    @Override
    public Node canonical(CanonicalizerTool tool) {
        ConstantNode callerClassNode = getCallerClassNode(tool.getMetaAccess(), tool.getConstantReflection());
        if (callerClassNode != null) {
            return callerClassNode;
        }
        return this;
    }

    @Override
    public void lower(LoweringTool tool) {
        ConstantNode callerClassNode = getCallerClassNode(tool.getMetaAccess(), tool.getConstantReflection());

        if (callerClassNode != null) {
            graph().replaceFixedWithFloating(this, graph().addOrUniqueWithInputs(callerClassNode));
        } else {
            InvokeNode invoke = createInvoke();
            graph().replaceFixedWithFixed(this, invoke);
            invoke.lower(tool);
        }
    }

    /**
     * If inlining is deep enough this method returns a {@link ConstantNode} of the caller class by
     * walking the stack.
     *
     * @param metaAccess
     * @return ConstantNode of the caller class, or null
     */
    private ConstantNode getCallerClassNode(MetaAccessProvider metaAccess, ConstantReflectionProvider constantReflection) {
        // Walk back up the frame states to find the caller at the required depth.
        FrameState state = stateAfter();

        // Cf. JVM_GetCallerClass
        // NOTE: Start the loop at depth 1 because the current frame state does
        // not include the Reflection.getCallerClass() frame.
        for (int n = 1; state != null; state = state.outerFrameState(), n++) {
            ResolvedJavaMethod method = state.getMethod();
            switch (n) {
                case 0:
                    throw GraalError.shouldNotReachHere("current frame state does not include the Reflection.getCallerClass frame");
                case 1:
                    // Frame 0 and 1 must be caller sensitive (see JVM_GetCallerClass).
                    if (!isCallerSensitive(method)) {
                        return null;  // bail-out; let JVM_GetCallerClass do the work
                    }
                    break;
                default:
                    if (!ignoredBySecurityStackWalk(method)) {
                        // We have reached the desired frame; return the holder class.
                        ResolvedJavaType callerClass = method.getDeclaringClass();
                        return ConstantNode.forConstant(constantReflection.asJavaClass(callerClass), metaAccess);
                    }
                    break;
            }
        }
        return null;  // bail-out; let JVM_GetCallerClass do the work
    }

    protected abstract boolean isCallerSensitive(ResolvedJavaMethod method);

    protected abstract boolean ignoredBySecurityStackWalk(ResolvedJavaMethod method);
}

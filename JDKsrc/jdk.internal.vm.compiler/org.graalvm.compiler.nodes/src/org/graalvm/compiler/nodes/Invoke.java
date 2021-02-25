/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes;

import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.nodes.CallTargetNode.InvokeKind;
import org.graalvm.compiler.nodes.java.MethodCallTargetNode;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.type.StampTool;

import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.ResolvedJavaType;

public interface Invoke extends StateSplit, Lowerable, SingleMemoryKill, DeoptimizingNode.DeoptDuring, FixedNodeInterface, Invokable {

    FixedNode next();

    void setNext(FixedNode x);

    CallTargetNode callTarget();

    Node predecessor();

    ValueNode classInit();

    void setClassInit(ValueNode node);

    boolean useForInlining();

    void setUseForInlining(boolean value);

    /**
     * True if this invocation is almost certainly polymorphic, false when in doubt.
     */
    boolean isPolymorphic();

    void setPolymorphic(boolean value);

    @Override
    default ResolvedJavaMethod getTargetMethod() {
        return callTarget() != null ? callTarget().targetMethod() : null;
    }

    @Override
    default ResolvedJavaMethod getContextMethod() {
        FrameState state = stateAfter();
        if (state == null) {
            state = stateDuring();
        }
        return state.getMethod();
    }

    /**
     * Returns the {@linkplain ResolvedJavaType type} from which this invoke is executed. This is
     * the declaring type of the caller method.
     *
     * @return the type from which this invoke is executed.
     */
    default ResolvedJavaType getContextType() {
        ResolvedJavaMethod contextMethod = getContextMethod();
        if (contextMethod == null) {
            return null;
        }
        return contextMethod.getDeclaringClass();
    }

    @Override
    default void computeStateDuring(FrameState stateAfter) {
        FrameState newStateDuring = stateAfter.duplicateModifiedDuringCall(bci(), asNode().getStackKind());
        setStateDuring(newStateDuring);
    }

    default ValueNode getReceiver() {
        assert getInvokeKind().hasReceiver();
        return callTarget().arguments().get(0);
    }

    default ResolvedJavaType getReceiverType() {
        ResolvedJavaType receiverType = StampTool.typeOrNull(getReceiver());
        if (receiverType == null) {
            receiverType = ((MethodCallTargetNode) callTarget()).targetMethod().getDeclaringClass();
        }
        return receiverType;
    }

    default InvokeKind getInvokeKind() {
        return callTarget().invokeKind();
    }

}

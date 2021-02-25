/*
 * Copyright (c) 2009, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.java;

import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.MetaAccessProvider;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.core.common.type.TypeReference;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.AbstractBeginNode;
import org.graalvm.compiler.nodes.BeginNode;
import org.graalvm.compiler.nodes.BeginStateSplitNode;
import org.graalvm.compiler.nodes.KillingBeginNode;
import org.graalvm.compiler.nodes.MultiKillingBeginNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.memory.MultiMemoryKill;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.spi.LoweringTool;
import jdk.internal.vm.compiler.word.LocationIdentity;

import static org.graalvm.compiler.nodeinfo.InputType.Memory;
import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_8;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_8;

/**
 * The entry to an exception handler with the exception coming from a call (as opposed to a local
 * throw instruction or implicit exception).
 */
@NodeInfo(allowedUsageTypes = Memory, cycles = CYCLES_8, size = SIZE_8)
public final class ExceptionObjectNode extends BeginStateSplitNode implements Lowerable, SingleMemoryKill {
    public static final NodeClass<ExceptionObjectNode> TYPE = NodeClass.create(ExceptionObjectNode.class);

    public ExceptionObjectNode(MetaAccessProvider metaAccess) {
        super(TYPE, StampFactory.objectNonNull(TypeReference.createTrustedWithoutAssumptions(metaAccess.lookupJavaType(Throwable.class))));
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return LocationIdentity.any();
    }

    /**
     * An exception handler is an entry point to a method from the runtime and so represents an
     * instruction that cannot be re-executed. It therefore needs a frame state.
     */
    @Override
    public boolean hasSideEffect() {
        return true;
    }

    @Override
    public void lower(LoweringTool tool) {
        if (graph().getGuardsStage() == StructuredGraph.GuardsStage.FIXED_DEOPTS) {
            /*
             * Now the lowering to BeginNode+LoadExceptionNode can be performed, since no more
             * deopts can float in between the begin node and the load exception node.
             */
            Node predecessor = predecessor();
            AbstractBeginNode entry;
            if (predecessor instanceof SingleMemoryKill) {
                LocationIdentity locationsKilledByPredecessor = ((SingleMemoryKill) predecessor).getKilledLocationIdentity();
                entry = graph().add(KillingBeginNode.create(locationsKilledByPredecessor));
            } else if (predecessor instanceof MultiMemoryKill) {
                LocationIdentity[] locationsKilledByPredecessor = ((MultiMemoryKill) predecessor).getKilledLocationIdentities();
                entry = graph().add(MultiKillingBeginNode.create(locationsKilledByPredecessor));
            } else {
                entry = graph().add(new BeginNode());
            }

            LoadExceptionObjectNode loadException = graph().add(new LoadExceptionObjectNode(stamp(NodeView.DEFAULT)));

            loadException.setStateAfter(stateAfter());
            replaceAtUsages(loadException, InputType.Value);
            graph().replaceFixedWithFixed(this, entry);
            entry.graph().addAfterFixed(entry, loadException);

            loadException.lower(tool);
        }
    }

    @Override
    public boolean verify() {
        assertTrue(stateAfter() != null, "an exception handler needs a frame state");
        assertTrue(stateAfter().stackSize() == 1 && stateAfter().stackAt(0).stamp(NodeView.DEFAULT).getStackKind() == JavaKind.Object,
                        "an exception handler's frame state must have only the exception on the stack");
        return super.verify();
    }
}

/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.replacements;

import static org.graalvm.compiler.nodeinfo.InputType.Memory;
import static org.graalvm.compiler.nodeinfo.InputType.State;
import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_0;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.DeoptimizingNode;
import org.graalvm.compiler.nodes.FrameState;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.memory.AbstractMemoryCheckpoint;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.spi.Lowerable;
import jdk.internal.vm.compiler.word.LocationIdentity;

@NodeInfo(cycles = CYCLES_2, size = SIZE_0, allowedUsageTypes = Memory)
public class FastNotifyNode extends AbstractMemoryCheckpoint implements Lowerable, SingleMemoryKill, DeoptimizingNode.DeoptDuring {

    public static final NodeClass<FastNotifyNode> TYPE = NodeClass.create(FastNotifyNode.class);
    private final boolean notifyAll;

    private final int bci;

    @Input ValueNode object;

    @OptionalInput(State) FrameState stateDuring;

    public FastNotifyNode(ValueNode object, boolean notifyAll, int bci) {
        super(TYPE, StampFactory.forVoid());
        this.object = object;
        this.notifyAll = notifyAll;
        this.bci = bci;
    }

    public boolean isNotifyAll() {
        return notifyAll;
    }

    @Override
    public LocationIdentity getKilledLocationIdentity() {
        return LocationIdentity.any();
    }

    @Override
    public FrameState stateDuring() {
        return stateDuring;
    }

    @Override
    public void setStateDuring(FrameState stateDuring) {
        updateUsages(this.stateDuring, stateDuring);
        this.stateDuring = stateDuring;
    }

    @Override
    public void computeStateDuring(FrameState currentStateAfter) {
        FrameState newStateDuring = currentStateAfter.duplicateModifiedDuringCall(bci, asNode().getStackKind());
        setStateDuring(newStateDuring);
    }

    @Override
    public boolean canDeoptimize() {
        return true;
    }

    public int getBci() {
        return bci;
    }
}

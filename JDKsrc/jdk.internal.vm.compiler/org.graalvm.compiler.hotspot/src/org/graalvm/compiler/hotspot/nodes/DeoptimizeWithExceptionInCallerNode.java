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


package org.graalvm.compiler.hotspot.nodes;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_8;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_8;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.hotspot.HotSpotLIRGenerator;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ControlSinkNode;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.spi.LIRLowerable;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;

import jdk.vm.ci.meta.Value;

/**
 * Removes the current frame and tail calls the uncommon trap routine.
 */
@NodeInfo(cycles = CYCLES_8, size = SIZE_8)
public final class DeoptimizeWithExceptionInCallerNode extends ControlSinkNode implements LIRLowerable {
    public static final NodeClass<DeoptimizeWithExceptionInCallerNode> TYPE = NodeClass.create(DeoptimizeWithExceptionInCallerNode.class);

    @Input protected ValueNode exception;

    public DeoptimizeWithExceptionInCallerNode(ValueNode exception) {
        super(TYPE, StampFactory.forVoid());
        this.exception = exception;
    }

    @Override
    public void generate(NodeLIRBuilderTool gen) {
        Value e = gen.operand(exception);
        ((HotSpotLIRGenerator) gen.getLIRGeneratorTool()).emitDeoptimizeWithExceptionInCaller(e);
    }

    @NodeIntrinsic
    public static native void deopt(Object exception);
}

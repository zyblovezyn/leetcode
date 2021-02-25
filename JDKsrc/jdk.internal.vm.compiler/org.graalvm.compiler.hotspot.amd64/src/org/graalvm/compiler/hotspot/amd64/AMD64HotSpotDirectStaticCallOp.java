/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.amd64;

import org.graalvm.compiler.asm.amd64.AMD64MacroAssembler;
import org.graalvm.compiler.hotspot.HotSpotMarkId;
import org.graalvm.compiler.lir.LIRFrameState;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.amd64.AMD64Call.DirectCallOp;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;
import org.graalvm.compiler.nodes.CallTargetNode.InvokeKind;

import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.Value;

/**
 * A direct call that complies with the conventions for such calls in HotSpot. It doesn't use an
 * inline cache so it's just a patchable call site.
 */
@Opcode("CALL_DIRECT")
final class AMD64HotSpotDirectStaticCallOp extends DirectCallOp {
    public static final LIRInstructionClass<AMD64HotSpotDirectStaticCallOp> TYPE = LIRInstructionClass.create(AMD64HotSpotDirectStaticCallOp.class);

    private final InvokeKind invokeKind;

    AMD64HotSpotDirectStaticCallOp(ResolvedJavaMethod target, Value result, Value[] parameters, Value[] temps, LIRFrameState state, InvokeKind invokeKind) {
        super(TYPE, target, result, parameters, temps, state);
        assert invokeKind.isDirect();
        this.invokeKind = invokeKind;
    }

    @Override
    @SuppressWarnings("try")
    public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
        try (CompilationResultBuilder.CallContext callContext = crb.openCallContext(invokeKind.isDirect())) {
            crb.recordMark(invokeKind == InvokeKind.Static ? HotSpotMarkId.INVOKESTATIC : HotSpotMarkId.INVOKESPECIAL);
            super.emitCode(crb, masm);
        }
    }
}

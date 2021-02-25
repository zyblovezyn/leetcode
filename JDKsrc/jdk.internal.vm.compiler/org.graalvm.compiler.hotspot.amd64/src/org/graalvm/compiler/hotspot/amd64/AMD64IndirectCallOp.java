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

import static jdk.vm.ci.code.ValueUtil.asRegister;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.REG;

import org.graalvm.compiler.asm.amd64.AMD64MacroAssembler;
import org.graalvm.compiler.hotspot.HotSpotMarkId;
import org.graalvm.compiler.lir.LIRFrameState;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.amd64.AMD64Call;
import org.graalvm.compiler.lir.amd64.AMD64Call.IndirectCallOp;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.amd64.AMD64;
import jdk.vm.ci.code.Register;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.Value;

/**
 * A register indirect call that complies with the extra conventions for such calls in HotSpot. In
 * particular, the metaspace Method of the callee must be in RBX for the case where a vtable entry's
 * _from_compiled_entry is the address of an C2I adapter. Such adapters expect the target method to
 * be in RBX.
 */
@Opcode("CALL_INDIRECT")
final class AMD64IndirectCallOp extends IndirectCallOp {
    public static final LIRInstructionClass<AMD64IndirectCallOp> TYPE = LIRInstructionClass.create(AMD64IndirectCallOp.class);

    /**
     * Vtable stubs expect the metaspace Method in RBX.
     */
    public static final Register METHOD = AMD64.rbx;

    @Use({REG}) protected Value metaspaceMethod;

    AMD64IndirectCallOp(ResolvedJavaMethod targetMethod, Value result, Value[] parameters, Value[] temps, Value metaspaceMethod, Value targetAddress, LIRFrameState state) {
        super(TYPE, targetMethod, result, parameters, temps, targetAddress, state);
        this.metaspaceMethod = metaspaceMethod;
    }

    @Override
    @SuppressWarnings("try")
    public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
        try (CompilationResultBuilder.CallContext callContext = crb.openCallContext(false)) {
            crb.recordMark(HotSpotMarkId.INLINE_INVOKE);
            Register callReg = asRegister(targetAddress);
            assert !callReg.equals(METHOD);
            AMD64Call.indirectCall(crb, masm, callReg, callTarget, state);
        }
    }

    @Override
    public void verify() {
        super.verify();
        assert asRegister(metaspaceMethod).equals(METHOD);
    }
}

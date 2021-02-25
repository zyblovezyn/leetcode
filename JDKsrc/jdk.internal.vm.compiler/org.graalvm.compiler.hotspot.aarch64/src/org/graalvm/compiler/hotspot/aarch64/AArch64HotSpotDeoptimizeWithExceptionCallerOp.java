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


package org.graalvm.compiler.hotspot.aarch64;

import static jdk.vm.ci.aarch64.AArch64.lr;
import static jdk.vm.ci.code.ValueUtil.asRegister;
import static org.graalvm.compiler.hotspot.HotSpotHostBackend.DEOPT_BLOB_UNPACK_WITH_EXCEPTION_IN_TLS;

import org.graalvm.compiler.asm.aarch64.AArch64MacroAssembler;
import org.graalvm.compiler.hotspot.GraalHotSpotVMConfig;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.aarch64.AArch64Call;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.code.Register;
import jdk.vm.ci.meta.Value;

/**
 * Removes the current frame and tail calls the uncommon trap routine.
 */
@Opcode("DEOPT_WITH_EXCEPTION_IN_CALLER")
public class AArch64HotSpotDeoptimizeWithExceptionCallerOp extends AArch64HotSpotEpilogueOp {
    public static final LIRInstructionClass<AArch64HotSpotDeoptimizeWithExceptionCallerOp> TYPE = LIRInstructionClass.create(AArch64HotSpotDeoptimizeWithExceptionCallerOp.class);

    @Use(OperandFlag.REG) private Value exception;

    public AArch64HotSpotDeoptimizeWithExceptionCallerOp(GraalHotSpotVMConfig config, Value exception, Register thread) {
        super(TYPE, config, thread);
        this.exception = exception;
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, AArch64MacroAssembler masm) {
        Register exc = asRegister(exception);

        leaveFrame(crb, masm, /* emitSafepoint */false, false);

        // Save exception oop in TLS
        masm.str(64, exc, masm.makeAddress(thread, config.threadExceptionOopOffset, 8));
        // Store original return address in TLS
        masm.str(64, lr, masm.makeAddress(thread, config.threadExceptionPcOffset, 8));

        AArch64Call.directJmp(crb, masm, crb.foreignCalls.lookupForeignCall(DEOPT_BLOB_UNPACK_WITH_EXCEPTION_IN_TLS));
    }
}

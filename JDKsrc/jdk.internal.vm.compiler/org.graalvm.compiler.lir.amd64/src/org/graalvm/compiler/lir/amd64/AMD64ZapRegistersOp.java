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


package org.graalvm.compiler.lir.amd64;

import org.graalvm.compiler.asm.amd64.AMD64MacroAssembler;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.StandardOp;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.amd64.AMD64Kind;
import jdk.vm.ci.code.Register;
import jdk.vm.ci.meta.JavaConstant;

/**
 * Writes well known garbage values to registers.
 */
@Opcode("ZAP_REGISTER")
public final class AMD64ZapRegistersOp extends AMD64LIRInstruction implements StandardOp.ZapRegistersOp {
    public static final LIRInstructionClass<AMD64ZapRegistersOp> TYPE = LIRInstructionClass.create(AMD64ZapRegistersOp.class);

    /**
     * The registers that are zapped.
     */
    protected final Register[] zappedRegisters;

    /**
     * The garbage values that are written to the registers.
     */
    protected final JavaConstant[] zapValues;

    public AMD64ZapRegistersOp(Register[] zappedRegisters, JavaConstant[] zapValues) {
        super(TYPE);
        this.zappedRegisters = zappedRegisters;
        this.zapValues = zapValues;
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
        for (int i = 0; i < zappedRegisters.length; i++) {
            Register reg = zappedRegisters[i];
            if (reg != null) {
                AMD64Move.const2reg(crb, masm, reg, zapValues[i], AMD64Kind.QWORD);
            }
        }
    }
}

/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.lir.amd64.vector;

import static jdk.vm.ci.code.ValueUtil.asRegister;
import static jdk.vm.ci.code.ValueUtil.isRegister;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.REG;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.STACK;

import org.graalvm.compiler.asm.amd64.AMD64Address;
import org.graalvm.compiler.asm.amd64.AMD64MacroAssembler;
import org.graalvm.compiler.asm.amd64.AVXKind;
import org.graalvm.compiler.asm.amd64.AMD64Assembler.VexRVMROp;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.amd64.AMD64LIRInstruction;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.meta.AllocatableValue;

public class AVXBlendOp extends AMD64LIRInstruction {
    public static final LIRInstructionClass<AVXBlendOp> TYPE = LIRInstructionClass.create(AVXBlendOp.class);

    @Opcode private final VexRVMROp opcode;
    private final AVXKind.AVXSize size;

    @Def({REG}) protected AllocatableValue result;
    @Use({REG}) protected AllocatableValue x;
    @Use({REG, STACK}) protected AllocatableValue y;
    @Use({REG}) protected AllocatableValue mask;

    public AVXBlendOp(VexRVMROp opcode, AVXKind.AVXSize size, AllocatableValue result, AllocatableValue x, AllocatableValue y, AllocatableValue mask) {
        super(TYPE);
        this.opcode = opcode;
        this.size = size;
        this.result = result;
        this.x = x;
        this.y = y;
        this.mask = mask;
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
        if (isRegister(y)) {
            opcode.emit(masm, size, asRegister(result), asRegister(mask), asRegister(x), asRegister(y));
        } else {
            opcode.emit(masm, size, asRegister(result), asRegister(mask), asRegister(x), (AMD64Address) crb.asAddress(y));
        }
    }
}

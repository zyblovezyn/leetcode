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
import org.graalvm.compiler.asm.amd64.AMD64Assembler.VexFloatCompareOp;
import org.graalvm.compiler.asm.amd64.AVXKind.AVXSize;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.amd64.AMD64LIRInstruction;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.meta.AllocatableValue;

public class AMD64VectorFloatCompareOp extends AMD64LIRInstruction {
    public static final LIRInstructionClass<AMD64VectorFloatCompareOp> TYPE = LIRInstructionClass.create(AMD64VectorFloatCompareOp.class);

    @Opcode private final VexFloatCompareOp opcode;
    private final AVXSize size;
    @Def({REG}) protected AllocatableValue result;
    @Use({REG}) protected AllocatableValue x;
    @Use({REG, STACK}) protected AllocatableValue y;
    private final VexFloatCompareOp.Predicate predicate;

    public AMD64VectorFloatCompareOp(VexFloatCompareOp opcode, AVXSize size, AllocatableValue result, AllocatableValue x, AllocatableValue y, VexFloatCompareOp.Predicate predicate) {
        super(TYPE);
        this.opcode = opcode;
        this.size = size;
        this.result = result;
        this.x = x;
        this.y = y;
        this.predicate = predicate;
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
        if (isRegister(y)) {
            opcode.emit(masm, size, asRegister(result), asRegister(x), asRegister(y), predicate);
        } else {
            opcode.emit(masm, size, asRegister(result), asRegister(x), (AMD64Address) crb.asAddress(y), predicate);
        }
    }

}

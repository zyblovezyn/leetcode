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


package org.graalvm.compiler.lir.amd64;

import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.COMPOSITE;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.REG;

import org.graalvm.compiler.asm.amd64.AMD64MacroAssembler;
import org.graalvm.compiler.core.common.LIRKind;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.amd64.AMD64;
import jdk.vm.ci.amd64.AMD64Kind;
import jdk.vm.ci.code.RegisterValue;
import jdk.vm.ci.meta.Value;

/**
 * Zeros a chunk of memory using rep stosb.
 */
@Opcode("ZERO_MEMORY")
public final class AMD64ZeroMemoryOp extends AMD64LIRInstruction {

    public static final LIRInstructionClass<AMD64ZeroMemoryOp> TYPE = LIRInstructionClass.create(AMD64ZeroMemoryOp.class);

    @Use({COMPOSITE}) protected AMD64AddressValue pointer;
    @Use({REG}) protected RegisterValue length;

    @Temp protected Value pointerTemp;
    @Temp protected Value valueTemp;
    @Temp protected Value lengthTemp;

    public AMD64ZeroMemoryOp(AMD64AddressValue pointer, RegisterValue length) {
        super(TYPE);
        this.pointer = pointer;
        this.length = length;

        this.pointerTemp = AMD64.rdi.asValue(LIRKind.value(AMD64Kind.QWORD));
        this.valueTemp = AMD64.rax.asValue(LIRKind.value(AMD64Kind.QWORD));
        this.lengthTemp = AMD64.rcx.asValue(LIRKind.value(AMD64Kind.QWORD));
    }

    @Override
    public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
        assert AMD64.rcx.equals(length.getRegister());
        masm.leaq(AMD64.rdi, pointer.toAddress());
        masm.xorq(AMD64.rax, AMD64.rax);
        masm.repStosb();
    }
}

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

import static jdk.vm.ci.code.ValueUtil.asRegister;
import static jdk.vm.ci.code.ValueUtil.isRegister;
import static jdk.vm.ci.code.ValueUtil.isStackSlot;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.HINT;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.REG;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.STACK;

import org.graalvm.compiler.asm.amd64.AMD64Address;
import org.graalvm.compiler.asm.amd64.AMD64Assembler.VexRVMOp;
import org.graalvm.compiler.asm.amd64.AMD64MacroAssembler;
import org.graalvm.compiler.asm.amd64.AVXKind.AVXSize;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.amd64.vector.AMD64VectorInstruction;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.meta.AllocatableValue;

/**
 * AMD64 LIR instructions that have three inputs and one output.
 */
public class AMD64Ternary {

    /**
     * Instruction that has two {@link AllocatableValue} operands.
     */
    public static class ThreeOp extends AMD64VectorInstruction {
        public static final LIRInstructionClass<ThreeOp> TYPE = LIRInstructionClass.create(ThreeOp.class);

        @Opcode private final VexRVMOp opcode;

        @Def({REG, HINT}) protected AllocatableValue result;
        @Use({REG}) protected AllocatableValue x;
        /**
         * This argument must be Alive to ensure that result and y are not assigned to the same
         * register, which would break the code generation by destroying y too early.
         */
        @Alive({REG}) protected AllocatableValue y;
        @Alive({REG, STACK}) protected AllocatableValue z;

        public ThreeOp(VexRVMOp opcode, AVXSize size, AllocatableValue result, AllocatableValue x, AllocatableValue y, AllocatableValue z) {
            super(TYPE, size);
            this.opcode = opcode;
            this.result = result;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public void emitCode(CompilationResultBuilder crb, AMD64MacroAssembler masm) {
            AMD64Move.move(crb, masm, result, x);
            if (isRegister(z)) {
                opcode.emit(masm, size, asRegister(result), asRegister(y), asRegister(z));
            } else {
                assert isStackSlot(z);
                opcode.emit(masm, size, asRegister(result), asRegister(y), (AMD64Address) crb.asAddress(z));
            }
        }
    }
}

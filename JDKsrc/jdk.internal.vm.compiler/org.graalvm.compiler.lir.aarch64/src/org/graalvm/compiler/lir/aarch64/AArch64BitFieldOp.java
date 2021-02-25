/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Arm Limited and affiliates. All rights reserved.
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



package org.graalvm.compiler.lir.aarch64;

import jdk.vm.ci.code.Register;
import jdk.vm.ci.meta.AllocatableValue;
import org.graalvm.compiler.asm.aarch64.AArch64MacroAssembler;
import org.graalvm.compiler.debug.GraalError;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import static jdk.vm.ci.code.ValueUtil.asRegister;
import static org.graalvm.compiler.lir.LIRInstruction.OperandFlag.REG;

/**
 * Bit field ops for AArch64.
 */
public class AArch64BitFieldOp extends AArch64LIRInstruction {
    public enum BitFieldOpCode {
        UBFX,
        UBFIZ,
    }

    private static final LIRInstructionClass<AArch64BitFieldOp> TYPE = LIRInstructionClass.create(AArch64BitFieldOp.class);

    @Opcode private final AArch64BitFieldOp.BitFieldOpCode opcode;
    @Def protected AllocatableValue result;
    @Use({REG}) protected AllocatableValue input;
    private final int lsb;
    private final int width;

    public AArch64BitFieldOp(AArch64BitFieldOp.BitFieldOpCode opcode, AllocatableValue result,
                    AllocatableValue input, int lsb, int width) {
        super(TYPE);
        this.opcode = opcode;
        this.result = result;
        this.input = input;
        this.lsb = lsb;
        this.width = width;
    }

    @Override
    protected void emitCode(CompilationResultBuilder crb, AArch64MacroAssembler masm) {
        Register dst = asRegister(result);
        Register src = asRegister(input);
        final int size = input.getPlatformKind().getSizeInBytes() * Byte.SIZE;
        switch (opcode) {
            case UBFX:
                masm.ubfm(size, dst, src, lsb, lsb + width - 1);
                break;
            case UBFIZ:
                masm.ubfm(size, dst, src, size - lsb, width - 1);
                break;
            default:
                throw GraalError.shouldNotReachHere();
        }
    }
}

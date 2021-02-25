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


package org.graalvm.compiler.lir.aarch64;

import static jdk.vm.ci.code.ValueUtil.asStackSlot;
import static jdk.vm.ci.code.ValueUtil.isStackSlot;

import jdk.internal.vm.compiler.collections.EconomicSet;
import org.graalvm.compiler.asm.aarch64.AArch64MacroAssembler;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Opcode;
import org.graalvm.compiler.lir.StandardOp.SaveRegistersOp;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.code.Register;
import jdk.vm.ci.code.StackSlot;
import jdk.vm.ci.meta.AllocatableValue;

/**
 * Saves registers to stack slots.
 */
@Opcode("SAVE_REGISTER")
public class AArch64SaveRegistersOp extends SaveRegistersOp {
    public static final LIRInstructionClass<AArch64SaveRegistersOp> TYPE = LIRInstructionClass.create(AArch64SaveRegistersOp.class);

    /**
     *
     * @param savedRegisters the registers saved by this operation which may be subject to
     *            {@linkplain #remove(EconomicSet) pruning}
     * @param savedRegisterLocations the slots to which the registers are saved
     */
    public AArch64SaveRegistersOp(Register[] savedRegisters, AllocatableValue[] savedRegisterLocations) {
        super(TYPE, savedRegisters, savedRegisterLocations);
    }

    protected void saveRegister(CompilationResultBuilder crb, AArch64MacroAssembler masm, StackSlot result, Register input) {
        AArch64Move.reg2stack(crb, masm, result, input.asValue());
    }

    @Override
    public void emitCode(CompilationResultBuilder crb) {
        AArch64MacroAssembler masm = (AArch64MacroAssembler) crb.asm;
        for (int i = 0; i < savedRegisters.length; i++) {
            if (savedRegisters[i] != null) {
                assert isStackSlot(slots[i]) : "not a StackSlot: " + slots[i];
                saveRegister(crb, masm, asStackSlot(slots[i]), savedRegisters[i]);
            }
        }
    }
}

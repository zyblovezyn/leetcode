/*
 * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.
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



package jdk.tools.jaotc.amd64;

import static jdk.vm.ci.amd64.AMD64.rax;
import static jdk.vm.ci.amd64.AMD64.rbx;
import static jdk.vm.ci.amd64.AMD64.rip;

import jdk.tools.jaotc.StubInformation;
import jdk.tools.jaotc.ELFMacroAssembler;
import org.graalvm.compiler.asm.amd64.AMD64Address;
import org.graalvm.compiler.asm.amd64.AMD64MacroAssembler;

import jdk.vm.ci.code.TargetDescription;
import org.graalvm.compiler.options.OptionValues;

public final class AMD64ELFMacroAssembler extends AMD64MacroAssembler implements ELFMacroAssembler {

    private int currentEndOfInstruction;

    public AMD64ELFMacroAssembler(TargetDescription target, OptionValues optionValues) {
        super(target, optionValues);
    }

    @Override
    public int currentEndOfInstruction() {
        return currentEndOfInstruction;
    }

    @Override
    public byte[] getPLTJumpCode() {
        // The main dispatch instruction
        // jmpq *0x00000000(%rip)
        jmp(new AMD64Address(rip, 0));
        currentEndOfInstruction = position();

        // Align to 8 bytes
        align(8);

        return close(true);
    }

    @Override
    public byte[] getPLTStaticEntryCode(StubInformation stub) {
        // The main dispatch instruction
        // jmpq *0x00000000(%rip)
        jmp(new AMD64Address(rip, 0));
        stub.setDispatchJumpOffset(position());

        // C2I stub used to call interpreter.
        // mov 0x00000000(%rip),%rbx Loading Method*
        movq(rbx, new AMD64Address(rip, 0));
        stub.setMovOffset(position());

        // jmpq *0x00000000(%rip) [c2i addr]
        jmp(new AMD64Address(rip, 0));
        stub.setC2IJumpOffset(position());

        // Call to VM runtime to resolve the call.
        // jmpq *0x00000000(%rip)
        stub.setResolveJumpStart(position());
        jmp(new AMD64Address(rip, 0));
        stub.setResolveJumpOffset(position());
        currentEndOfInstruction = position();

        // Align to 8 bytes
        align(8);
        stub.setSize(position());

        return close(true);
    }

    @Override
    public byte[] getPLTVirtualEntryCode(StubInformation stub) {
        // Klass loading instruction
        // mov 0x00000000(%rip),%rax
        movq(rax, new AMD64Address(rip, 0));
        stub.setMovOffset(position());

        // The main dispatch instruction
        // jmpq *0x00000000(%rip)
        jmp(new AMD64Address(rip, 0));
        stub.setDispatchJumpOffset(position());

        // Call to VM runtime to resolve the call.
        // jmpq *0x00000000(%rip)
        stub.setResolveJumpStart(position());
        jmp(new AMD64Address(rip, 0));
        stub.setResolveJumpOffset(position());
        currentEndOfInstruction = position();

        // Align to 8 bytes
        align(8);
        stub.setSize(position());

        return close(true);
    }

}

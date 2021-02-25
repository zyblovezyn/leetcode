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



package jdk.tools.jaotc;

import org.graalvm.compiler.options.OptionValues;

import jdk.tools.jaotc.aarch64.AArch64ELFMacroAssembler;
import jdk.tools.jaotc.amd64.AMD64ELFMacroAssembler;
import jdk.vm.ci.aarch64.AArch64;
import jdk.vm.ci.amd64.AMD64;
import jdk.vm.ci.code.Architecture;
import jdk.vm.ci.code.TargetDescription;

public interface ELFMacroAssembler {

    static ELFMacroAssembler getELFMacroAssembler(TargetDescription target, OptionValues optionValues) {
        Architecture architecture = target.arch;
        if (architecture instanceof AMD64) {
            return new AMD64ELFMacroAssembler(target, optionValues);
        } else if (architecture instanceof AArch64) {
            return new AArch64ELFMacroAssembler(target);
        } else {
            throw new InternalError("Unsupported architecture " + architecture);
        }
    }

    int currentEndOfInstruction();

    byte[] getPLTJumpCode();

    byte[] getPLTStaticEntryCode(StubInformation stub);

    byte[] getPLTVirtualEntryCode(StubInformation stub);

}

/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.asm.amd64.AMD64Address;
import org.graalvm.compiler.core.common.LIRKind;
import org.graalvm.compiler.debug.GraalError;
import org.graalvm.compiler.lir.asm.ArrayDataPointerConstant;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;

import jdk.vm.ci.amd64.AMD64;
import jdk.vm.ci.amd64.AMD64Kind;
import jdk.vm.ci.code.Register;
import jdk.vm.ci.meta.Value;

public final class AMD64HotSpotHelper {

    private AMD64HotSpotHelper() {
    }

    protected static Value[] registersToValues(Register[] registers) {
        Value[] temps = new Value[registers.length];
        for (int i = 0; i < registers.length; i++) {
            Register register = registers[i];
            if (AMD64.CPU.equals(register.getRegisterCategory())) {
                temps[i] = register.asValue(LIRKind.value(AMD64Kind.QWORD));
            } else if (AMD64.XMM.equals(register.getRegisterCategory())) {
                temps[i] = register.asValue(LIRKind.value(AMD64Kind.DOUBLE));
            } else {
                throw GraalError.shouldNotReachHere("Unsupported register type in math stubs.");
            }
        }
        return temps;
    }

    protected static AMD64Address recordExternalAddress(CompilationResultBuilder crb, ArrayDataPointerConstant ptr) {
        return (AMD64Address) crb.recordDataReferenceInCode(ptr);
    }

    protected static ArrayDataPointerConstant pointerConstant(int alignment, int[] ints) {
        return new ArrayDataPointerConstant(ints, alignment);
    }
}

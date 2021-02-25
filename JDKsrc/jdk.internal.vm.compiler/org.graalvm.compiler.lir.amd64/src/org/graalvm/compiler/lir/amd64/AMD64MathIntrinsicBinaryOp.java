/*
 * Copyright (c) 2011, 2019, Oracle and/or its affiliates. All rights reserved.
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

import static jdk.vm.ci.amd64.AMD64.xmm0;
import static jdk.vm.ci.amd64.AMD64.xmm1;
import static org.graalvm.compiler.lir.amd64.AMD64HotSpotHelper.registersToValues;

import org.graalvm.compiler.core.common.LIRKind;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.Variable;
import org.graalvm.compiler.lir.gen.LIRGenerator;

import jdk.vm.ci.amd64.AMD64Kind;
import jdk.vm.ci.code.Register;
import jdk.vm.ci.code.RegisterValue;
import jdk.vm.ci.meta.Value;

/**
 * AMD64MathIntrinsicBinaryOp assumes that the input values are stored in the xmm0 and xmm1
 * registers, and it will emit the output value into the xmm0 register.
 * {@link #emitLIRWrapper(LIRGenerator, Value, Value)} is provided for emitting necessary mov LIRs
 * before and after this LIR instruction.
 */
public abstract class AMD64MathIntrinsicBinaryOp extends AMD64LIRInstruction {

    @Def protected Value output;
    @Use protected Value input0;
    @Use protected Value input1;
    @Temp protected Value[] temps;

    public AMD64MathIntrinsicBinaryOp(LIRInstructionClass<? extends AMD64MathIntrinsicBinaryOp> type, Register... registers) {
        super(type);

        input0 = xmm0.asValue(LIRKind.value(AMD64Kind.DOUBLE));
        input1 = xmm1.asValue(LIRKind.value(AMD64Kind.DOUBLE));
        output = xmm0.asValue(LIRKind.value(AMD64Kind.DOUBLE));

        temps = registersToValues(registers);
    }

    public final Variable emitLIRWrapper(LIRGenerator gen, Value x, Value y) {
        LIRKind kind = LIRKind.combine(x, y);
        RegisterValue xmm0Value = xmm0.asValue(kind);
        gen.emitMove(xmm0Value, x);
        RegisterValue xmm1Value = xmm1.asValue(kind);
        gen.emitMove(xmm1Value, y);
        gen.append(this);
        Variable result = gen.newVariable(kind);
        gen.emitMove(result, xmm0Value);
        return result;
    }
}

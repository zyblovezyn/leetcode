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

import org.graalvm.compiler.asm.amd64.AVXKind.AVXSize;
import org.graalvm.compiler.lir.LIRInstructionClass;
import org.graalvm.compiler.lir.amd64.AMD64LIRInstruction;

public abstract class AMD64VectorInstruction extends AMD64LIRInstruction {

    public static final LIRInstructionClass<AMD64VectorInstruction> TYPE = LIRInstructionClass.create(AMD64VectorInstruction.class);
    protected final AVXSize size;

    public AMD64VectorInstruction(LIRInstructionClass<? extends AMD64VectorInstruction> c, AVXSize size) {
        super(c);
        this.size = size;
    }

    @Override
    public boolean needsClearUpperVectorRegisters() {
        return size == AVXSize.YMM || size == AVXSize.ZMM;
    }

}

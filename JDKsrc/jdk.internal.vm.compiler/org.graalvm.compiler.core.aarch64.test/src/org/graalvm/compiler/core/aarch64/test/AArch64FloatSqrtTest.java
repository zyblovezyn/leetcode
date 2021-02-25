/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Arm Limited. All rights reserved.
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


package org.graalvm.compiler.core.aarch64.test;

import org.graalvm.compiler.lir.LIRInstruction;
import org.junit.Test;

import java.util.function.Predicate;

public class AArch64FloatSqrtTest extends AArch64MatchRuleTest {

    private static final Predicate<LIRInstruction> p1 = op -> op.name().equals("SQRT");
    private static final Predicate<LIRInstruction> p2 = op -> op.name().equals("AArch64FloatConvert");

    public float floatSqrt(float f) {
        return (float) Math.sqrt(f);
    }

    private float[] input = {-1, 0f, -0f, Float.MAX_VALUE, Float.MIN_NORMAL, Float.MIN_VALUE, Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY};

    @Test
    public void testFloatSqrt() {
        for (float f : input) {
            test("floatSqrt", f);
            checkLIR("floatSqrt", p1, 1);
            checkLIR("floatSqrt", p2, 0);
        }
    }
}

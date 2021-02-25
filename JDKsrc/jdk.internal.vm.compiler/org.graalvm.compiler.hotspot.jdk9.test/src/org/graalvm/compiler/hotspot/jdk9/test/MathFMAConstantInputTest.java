/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.jdk9.test;

import static org.junit.Assume.assumeFalse;

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.junit.Before;
import org.junit.Test;

public final class MathFMAConstantInputTest extends GraalCompilerTest {

    @Before
    public void checkNotSPARC() {
        assumeFalse("skipping test on SPARC ", isSPARC(getTarget().arch));
    }

    public static float floatFMA() {
        return Math.fma(2.0f, 2.0f, 2.0f);
    }

    @Test
    public void testFloatFMA() {
        test("floatFMA");
    }

    public static float floatFMAWithPi() {
        float[] input = {Float.MAX_VALUE, 2.0F, -Float.MAX_VALUE};
        return Math.fma(input[0], input[1], input[2]);
    }

    @Test
    public void testFloatFMAWithPi() {
        test("floatFMAWithPi");
    }

    public static double doubleFMA() {
        return Math.fma(2.0d, 2.0d, 2.0d);
    }

    @Test
    public void testDoubleFMA() {
        test("doubleFMA");
    }

    public static double doubleFMAWithPi() {
        double[] input = {Double.MAX_VALUE, 2.0D, -Double.MAX_VALUE};
        return Math.fma(input[0], input[1], input[2]);
    }

    @Test
    public void testDoubleFMAWithPi() {
        test("doubleFMAWithPi");
    }

}

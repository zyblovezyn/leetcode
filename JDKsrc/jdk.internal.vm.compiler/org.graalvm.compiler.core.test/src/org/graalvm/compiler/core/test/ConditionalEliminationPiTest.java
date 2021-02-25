/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core.test;

import org.junit.Test;

public class ConditionalEliminationPiTest extends ConditionalEliminationTestBase {

    static int SideEffect;

    static double oracleValue1 = -0.0;
    static double oracleValue2;

    public static double testSnippet1(int a) {
        double phi;
        if (a > 0) {
            double oracle = oracleValue1;
            if (oracle == 0.0) {
                SideEffect = 1;
            } else {
                return 123;
            }
            phi = oracle;
        } else {
            double oracle = oracleValue2;
            if (oracle == 0.0) {
                SideEffect = 1;
                phi = oracle;
            } else {
                return 0;
            }
        }
        if (Double.doubleToRawLongBits(phi) == Double.doubleToRawLongBits(-0.0)) {
            return 12;
        }
        return 2;
    }

    @Test
    public void test1() {
        test("testSnippet1", 1);
    }
}

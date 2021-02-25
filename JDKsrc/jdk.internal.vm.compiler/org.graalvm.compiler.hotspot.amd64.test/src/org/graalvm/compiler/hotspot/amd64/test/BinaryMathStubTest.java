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


package org.graalvm.compiler.hotspot.amd64.test;

import static org.junit.Assume.assumeTrue;

import java.util.ArrayList;
import java.util.List;

import org.graalvm.compiler.api.test.Graal;
import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.runtime.RuntimeProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import jdk.vm.ci.amd64.AMD64;
import jdk.vm.ci.code.Architecture;

@RunWith(Parameterized.class)
public class BinaryMathStubTest extends GraalCompilerTest {

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        ArrayList<Object[]> ret = new ArrayList<>();
        ret.add(new Object[]{"pow"});
        return ret;
    }

    private static final double[] inputs = {0.0D, Math.PI / 2, Math.PI, -1.0D, Double.MAX_VALUE, Double.MIN_VALUE, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
    private final String stub;

    public BinaryMathStubTest(String stub) {
        this.stub = stub;
    }

    @Before
    public void checkAMD64() {
        Architecture arch = Graal.getRequiredCapability(RuntimeProvider.class).getHostBackend().getTarget().arch;
        assumeTrue("skipping AMD64 specific test", arch instanceof AMD64);
    }

    public static double pow(double x, double y) {
        return Math.pow(x, y);
    }

    @Test
    public void testStub() {
        for (double x : inputs) {
            for (double y : inputs) {
                test(stub, x, y);
            }
        }
    }
}

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


package org.graalvm.compiler.hotspot.test;

import static org.graalvm.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration.BytecodeExceptionMode.CheckAll;

import java.io.IOException;
import java.util.List;

import org.graalvm.compiler.core.test.SubprocessTest;
import org.graalvm.compiler.hotspot.HotSpotBackend;
import org.graalvm.compiler.hotspot.stubs.CreateExceptionStub;
import org.graalvm.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration;
import org.junit.Assume;
import org.junit.Test;

/**
 * This test exercises the deoptimization in the BytecodeExceptioNode foreign call path.
 */
public class HotSpotDeoptExplicitExceptions extends SubprocessTest {

    @Override
    protected GraphBuilderConfiguration editGraphBuilderConfiguration(GraphBuilderConfiguration conf) {
        return super.editGraphBuilderConfiguration(conf).withBytecodeExceptionMode(CheckAll);
    }

    static String nullCheckSnippet(Object o) {
        return o.toString();
    }

    static int divByZeroSnippet(int x, int y) {
        return x / y;
    }

    static String classCastSnippet(Object o) {
        return (String) o;
    }

    void testBody() {
        test("nullCheckSnippet", (Object) null);
        test("divByZeroSnippet", 1, 0);
        test("classCastSnippet", Boolean.TRUE);
    }

    @Override
    public void configSubprocess(List<String> vmArgs) {
        vmArgs.add("-Dgraal.HotSpotDeoptExplicitExceptions=true");
    }

    @Test
    public void explicitExceptions() throws IOException, InterruptedException {
        Assume.assumeTrue("required entry point is missing", ((HotSpotBackend) getBackend()).getRuntime().getVMConfig().deoptBlobUnpackWithExceptionInTLS != 0);
        if (!CreateExceptionStub.Options.HotSpotDeoptExplicitExceptions.getValue(getInitialOptions())) {
            launchSubprocess(this::testBody);
        } else {
            testBody();
        }
    }

}

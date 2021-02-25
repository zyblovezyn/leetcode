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


package org.graalvm.compiler.core.test;

import static org.graalvm.compiler.test.SubprocessUtil.getVMCommandLine;
import static org.graalvm.compiler.test.SubprocessUtil.java;
import static org.graalvm.compiler.test.SubprocessUtil.withoutDebuggerArguments;

import java.io.IOException;
import java.util.List;

import org.graalvm.compiler.test.SubprocessUtil;
import org.junit.Assume;
import org.junit.Before;

public abstract class SubprocessTest extends GraalCompilerTest {

    @Before
    public void checkJavaAgent() {
        Assume.assumeFalse("Java Agent found -> skipping", SubprocessUtil.isJavaAgentAttached());
    }

    public void launchSubprocess(Runnable runnable) throws InterruptedException, IOException {
        String recursionPropName = getClass().getSimpleName() + ".Subprocess";
        if (Boolean.getBoolean(recursionPropName)) {
            runnable.run();
        } else {
            List<String> vmArgs = withoutDebuggerArguments(getVMCommandLine());
            vmArgs.add(SubprocessUtil.PACKAGE_OPENING_OPTIONS);
            vmArgs.add("-D" + recursionPropName + "=true");
            configSubprocess(vmArgs);
            boolean verbose = Boolean.getBoolean(getClass().getSimpleName() + ".verbose");
            if (verbose) {
                System.err.println(String.join(" ", vmArgs));
            }
            SubprocessUtil.Subprocess proc = java(vmArgs, "com.oracle.mxtool.junit.MxJUnitWrapper", getClass().getName());
            if (verbose) {
                System.err.println(proc.output);
            }
            assertTrue(proc.exitCode == 0, proc.toString() + " failed with exit code " + proc.exitCode);
        }
    }

    @SuppressWarnings("unused")
    public void configSubprocess(List<String> vmArgs) {
    }

}

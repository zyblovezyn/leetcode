/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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

import static org.graalvm.compiler.core.GraalCompilerOptions.CompilationBailoutAsFailure;
import static org.graalvm.compiler.core.GraalCompilerOptions.CompilationFailureAction;

import org.graalvm.compiler.core.CompilationWrapper.ExceptionAction;
import org.graalvm.compiler.core.phases.HighTier;
import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.debug.DebugOptions;
import org.graalvm.compiler.hotspot.HotSpotGraalCompiler;
import org.graalvm.compiler.options.OptionValues;
import org.junit.Test;

import jdk.vm.ci.hotspot.HotSpotJVMCIRuntime;

/**
 * Tests {@link CompileTheWorld} functionality.
 */
public class CompileTheWorldTest extends GraalCompilerTest {

    @Test
    public void testJDK() throws Throwable {
        boolean originalBailoutAction = CompilationBailoutAsFailure.getValue(getInitialOptions());
        ExceptionAction originalFailureAction = CompilationFailureAction.getValue(getInitialOptions());
        // Compile a couple classes in rt.jar
        HotSpotJVMCIRuntime runtime = HotSpotJVMCIRuntime.runtime();
        System.setProperty("CompileTheWorld.LimitModules", "java.base");
        OptionValues initialOptions = getInitialOptions();
        OptionValues harnessOptions = CompileTheWorld.loadHarnessOptions();
        int startAt = 1;
        int stopAt = 5;
        int maxClasses = Integer.MAX_VALUE;
        String methodFilters = null;
        String excludeMethodFilters = null;
        boolean verbose = false;
        CompileTheWorld ctw = new CompileTheWorld(runtime,
                        (HotSpotGraalCompiler) runtime.getCompiler(),
                        CompileTheWorld.SUN_BOOT_CLASS_PATH,
                        startAt,
                        stopAt,
                        maxClasses,
                        methodFilters,
                        excludeMethodFilters,
                        verbose,
                        harnessOptions,
                        new OptionValues(initialOptions, HighTier.Options.Inline, false,
                                        DebugOptions.DisableIntercept, true,
                                        CompilationFailureAction, ExceptionAction.Silent));
        ctw.compile();
        assert CompilationBailoutAsFailure.getValue(initialOptions) == originalBailoutAction;
        assert CompilationFailureAction.getValue(initialOptions) == originalFailureAction;
    }
}

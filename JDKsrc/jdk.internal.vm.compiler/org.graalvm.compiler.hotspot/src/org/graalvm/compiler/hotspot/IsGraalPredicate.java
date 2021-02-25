/*
 * Copyright (c) 2018, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot;

import static jdk.vm.ci.hotspot.HotSpotJVMCICompilerFactory.CompilationLevelAdjustment.None;

import org.graalvm.compiler.debug.GraalError;

import jdk.vm.ci.hotspot.HotSpotJVMCICompilerFactory;
import jdk.vm.ci.hotspot.HotSpotJVMCIRuntime;

/**
 * Determines if a given class is a JVMCI or Graal class for the purpose of
 * {@link HotSpotGraalCompilerFactory.Options#CompileGraalWithC1Only}.
 */
class IsGraalPredicate extends IsGraalPredicateBase {
    /**
     * Module containing {@link HotSpotJVMCICompilerFactory}.
     */
    private final Module jvmciModule;

    /**
     * Module containing {@link HotSpotGraalCompilerFactory}.
     */
    private final Module graalModule;

    /**
     * Module containing the {@linkplain CompilerConfigurationFactory#selectFactory selected}
     * configuration.
     */
    private Module compilerConfigurationModule;

    IsGraalPredicate() {
        jvmciModule = HotSpotJVMCICompilerFactory.class.getModule();
        graalModule = HotSpotGraalCompilerFactory.class.getModule();
    }

    @Override
    void onCompilerConfigurationFactorySelection(HotSpotJVMCIRuntime runtime, CompilerConfigurationFactory factory) {
        compilerConfigurationModule = factory.getClass().getModule();
        runtime.excludeFromJVMCICompilation(jvmciModule, graalModule, compilerConfigurationModule);
    }

    @Override
    boolean apply(Class<?> declaringClass) {
        throw GraalError.shouldNotReachHere();
    }

    @Override
    HotSpotJVMCICompilerFactory.CompilationLevelAdjustment getCompilationLevelAdjustment() {
        return None;
    }

}

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

import jdk.vm.ci.hotspot.HotSpotJVMCICompilerFactory;
import jdk.vm.ci.hotspot.HotSpotJVMCIRuntime;

/**
 * Determines if a given class is a JVMCI or Graal class for the purpose of
 * {@link HotSpotGraalCompilerFactory.Options#CompileGraalWithC1Only}.
 */
abstract class IsGraalPredicateBase {

    @SuppressWarnings("unused")
    void onCompilerConfigurationFactorySelection(HotSpotJVMCIRuntime runtime, CompilerConfigurationFactory factory) {
    }

    abstract boolean apply(Class<?> declaringClass);

    HotSpotJVMCICompilerFactory.CompilationLevelAdjustment getCompilationLevelAdjustment() {
        return HotSpotJVMCICompilerFactory.CompilationLevelAdjustment.ByHolder;
    }
}

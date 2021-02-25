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


package org.graalvm.compiler.replacements;

import org.graalvm.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration.Plugins;
import org.graalvm.compiler.nodes.spi.Replacements;

import jdk.vm.ci.code.Architecture;

public interface TargetGraphBuilderPlugins {
    void register(Plugins plugins, Replacements replacements, Architecture arch, boolean explicitUnsafeNullChecks, boolean registerForeignCallMath, boolean emitJDK9StringSubstitutions,
                    boolean useFMAIntrinsics);
}

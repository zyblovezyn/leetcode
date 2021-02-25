/*
 * Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.test;

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.debug.DebugContext.Builder;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.StructuredGraph.AllowAssumptions;
import org.graalvm.compiler.options.OptionValues;
import org.junit.Assert;
import org.junit.Test;

import jdk.vm.ci.meta.ResolvedJavaMethod;

public class StructuredGraphTest extends GraalCompilerTest {

    @Test
    public void testGetBytecodeSize() {
        OptionValues options = getInitialOptions();
        DebugContext debug = new Builder(options).build();
        ResolvedJavaMethod rootMethod = getResolvedJavaMethod("testGetBytecodeSize");

        // Test graph with root method and inlined methods
        StructuredGraph graph = new StructuredGraph.Builder(options, debug, AllowAssumptions.YES).method(rootMethod).build();
        ResolvedJavaMethod otherMethod = getResolvedJavaMethod(GraalCompilerTest.class, "createSuites");
        int expectedBytecodeSize = rootMethod.getCodeSize();
        for (int i = 0; i < 10; i++) {
            graph.recordMethod(otherMethod);
            expectedBytecodeSize += otherMethod.getCodeSize();
        }
        Assert.assertEquals(expectedBytecodeSize, graph.getBytecodeSize());

        // Test graph with only root method, no inlined methods
        graph = new StructuredGraph.Builder(options, debug, AllowAssumptions.YES).method(rootMethod).build();
        expectedBytecodeSize = rootMethod.getCodeSize();
        Assert.assertEquals(expectedBytecodeSize, graph.getBytecodeSize());

        // Test graph with no root method, only inlined methods
        graph = new StructuredGraph.Builder(options, debug, AllowAssumptions.YES).build();
        expectedBytecodeSize = 0;
        for (int i = 0; i < 10; i++) {
            graph.recordMethod(otherMethod);
            expectedBytecodeSize += otherMethod.getCodeSize();
        }
        Assert.assertEquals(expectedBytecodeSize, graph.getBytecodeSize());

        // Test graph with no root method, no inlined methods
        graph = new StructuredGraph.Builder(options, debug, AllowAssumptions.YES).build();
        expectedBytecodeSize = 0;
        Assert.assertEquals(expectedBytecodeSize, graph.getBytecodeSize());
    }
}

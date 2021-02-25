/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.printer.CanonicalStringGraphPrinter;
import org.junit.Test;

import jdk.vm.ci.meta.JavaConstant;

/**
 * Tests related to graph printing.
 */
public class GraphPrinterTest extends GraalCompilerTest {

    /**
     * Tests that a self-recursive object does not cause stack overflow when formatted as a string.
     */
    @Test
    public void testGraphPrinterDoesNotStackOverflow() {
        CanonicalStringGraphPrinter printer = new CanonicalStringGraphPrinter(getSnippetReflection());
        Object[] topArray = {null};
        Object[] parent = topArray;
        Object[] lastArray = null;
        for (int i = 0; i < 5; i++) {
            lastArray = new Object[1];
            parent[0] = lastArray;
            parent = lastArray;
        }
        lastArray[0] = topArray;
        JavaConstant constant = getSnippetReflection().forObject(topArray);
        printer.format(constant);
    }
}

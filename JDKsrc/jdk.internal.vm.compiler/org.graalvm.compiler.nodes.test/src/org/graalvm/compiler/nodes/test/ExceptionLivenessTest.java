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


package org.graalvm.compiler.nodes.test;

import static org.graalvm.compiler.java.BytecodeParserOptions.InlineDuringParsing;

import org.graalvm.compiler.core.phases.HighTier;
import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.options.OptionValues;
import org.junit.Test;

public class ExceptionLivenessTest extends GraalCompilerTest {
    @Test
    public void testNewarray() {
        OptionValues options = new OptionValues(getInitialOptions(), HighTier.Options.Inline, false, InlineDuringParsing, false);
        test(options, "newarraySnippet");
    }

    public static int[] newarraySnippet() {
        int[] array = new int[4];

        dummy();
        try {
            array = new int[-10];
        } catch (NegativeArraySizeException exc3) {
        }
        return array;
    }

    @BytecodeParserNeverInline
    static void dummy() {
    }
}

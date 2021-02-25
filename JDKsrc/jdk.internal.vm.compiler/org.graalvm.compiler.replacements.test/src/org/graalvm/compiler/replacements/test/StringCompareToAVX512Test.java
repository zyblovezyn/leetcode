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


package org.graalvm.compiler.replacements.test;

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.junit.Test;

// JDK-8228903
public final class StringCompareToAVX512Test extends GraalCompilerTest {

    public static int compareTo(String str1, String str2) {
        return str1.compareTo(str2);
    }

    @Test
    public void testLatin1VsUtf16BadStride() {
        String latin1 = "000000001111111122222222333333334444444455555555666666667777777\u0099888888889";
        String utf16 = "000000001111111122222222333333334444444455555555666666667777777\u0699888888889";
        test("compareTo", latin1, utf16);
        test("compareTo", utf16, latin1);
    }

    @Test
    public void testLatin1VsUtf16BadStride2() {
        String latin1 = "00000000111111112222222233333333444444445555555566666666777777778888888\u008799999999AAAAAAAAB";
        String utf16 = "00000000111111112222222233333333444444445555555566666666777777778888888\u058799999999AAAAAAAAB";
        test("compareTo", latin1, utf16);
        test("compareTo", utf16, latin1);
    }

    @Test
    public void testLatin1VsUtf16FalseEquality() {
        // java.lang.AssertionError: expected:<-1536> but was:<0>
        String latin1 = "00000000111111112222222233333333\u0099444444455555555";
        String utf16 = "00000000111111112222222233333333\u0699xxxxxxxxxxxxxxx";
        test("compareTo", latin1, utf16);
        test("compareTo", utf16, latin1);
    }

    @Test
    public void testLatin1BeyondRange() {
        StringBuilder latin1Builder = new StringBuilder();
        for (int j = 0; j <= 255; ++j) {
            latin1Builder.append((char) j);
        }
        String latin1 = latin1Builder.toString();
        test("compareTo", latin1, String.valueOf(latin1.toCharArray()));
    }
}

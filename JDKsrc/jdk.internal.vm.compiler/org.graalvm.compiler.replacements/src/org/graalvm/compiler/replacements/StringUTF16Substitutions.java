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


package org.graalvm.compiler.replacements;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.nodes.extended.JavaReadNode;
import org.graalvm.compiler.nodes.extended.JavaWriteNode;
import org.graalvm.compiler.nodes.graphbuilderconf.InvocationPlugin;

// JaCoCo Exclude

/**
 * Substitutions for {@code StringUTF16} methods for JDK9 and later.
 */
@ClassSubstitution(className = "java.lang.StringUTF16", optional = true)
public class StringUTF16Substitutions {

    @MethodSubstitution
    public static char getChar(byte[] value, int i) {
        ReplacementsUtil.dynamicAssert((i << 1) + 1 < value.length, "Trusted caller missed bounds check");
        return getCharDirect(value, i << 1);
    }

    /**
     * Will be intrinsified with an {@link InvocationPlugin} to a {@link JavaReadNode}.
     */
    public static native char getCharDirect(byte[] value, int i);

    @MethodSubstitution
    public static void putChar(byte[] value, int i, int c) {
        ReplacementsUtil.dynamicAssert((i << 1) + 1 < value.length, "Trusted caller missed bounds check");
        putCharDirect(value, i << 1, c);
    }

    /**
     * Will be intrinsified with an {@link InvocationPlugin} to a {@link JavaWriteNode}.
     */
    public static native void putCharDirect(byte[] value, int i, int c);
}

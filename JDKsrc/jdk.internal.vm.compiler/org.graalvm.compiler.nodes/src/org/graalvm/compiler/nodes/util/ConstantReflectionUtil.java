/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.util;

import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.JavaConstant;

/**
 * Helper functions to access complex objects on the application heap via the
 * {@link ConstantReflectionProvider}.
 */
public final class ConstantReflectionUtil {
    private ConstantReflectionUtil() {
    }

    public static byte[] loadByteArrayConstant(ConstantReflectionProvider crp, JavaConstant targetArg, int maxLength) {
        int targetArgLength = Integer.min(maxLength, crp.readArrayLength(targetArg));
        byte[] targetByteArray = new byte[targetArgLength];
        for (int i = 0; i < targetArgLength; i++) {
            targetByteArray[i] = (byte) crp.readArrayElement(targetArg, i).asInt();
        }
        return targetByteArray;
    }

    public static char[] loadCharArrayConstant(ConstantReflectionProvider crp, JavaConstant targetArg, int maxLength) {
        int targetArgLength = Integer.min(maxLength, crp.readArrayLength(targetArg));
        char[] targetCharArray = new char[targetArgLength];
        for (int i = 0; i < targetArgLength; i++) {
            targetCharArray[i] = (char) crp.readArrayElement(targetArg, i).asInt();
        }
        return targetCharArray;
    }

    public static int[] loadIntArrayConstant(ConstantReflectionProvider crp, JavaConstant targetArg, int maxLength) {
        int targetArgLength = Integer.min(maxLength, crp.readArrayLength(targetArg));
        int[] targetCharArray = new int[targetArgLength];
        for (int i = 0; i < targetArgLength; i++) {
            targetCharArray[i] = (char) crp.readArrayElement(targetArg, i).asInt();
        }
        return targetCharArray;
    }
}

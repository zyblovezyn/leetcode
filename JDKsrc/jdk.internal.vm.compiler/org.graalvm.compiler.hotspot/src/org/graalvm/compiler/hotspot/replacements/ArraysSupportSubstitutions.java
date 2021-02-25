/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Red Hat Inc. All rights reserved.
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



package org.graalvm.compiler.hotspot.replacements;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.hotspot.HotSpotBackend;
import org.graalvm.compiler.nodes.ComputeObjectAddressNode;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.WordFactory;

@ClassSubstitution(className = "jdk.internal.util.ArraysSupport", optional = true)
public class ArraysSupportSubstitutions {

    @SuppressWarnings("unused")
    @MethodSubstitution(isStatic = true)
    static int vectorizedMismatch(Object a, long aOffset, Object b, long bOffset, int length, int log2ArrayIndexScale) {
        Word aAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(a, aOffset));
        Word bAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(b, bOffset));

        return HotSpotBackend.vectorizedMismatch(aAddr, bAddr, length, log2ArrayIndexScale);
    }
}

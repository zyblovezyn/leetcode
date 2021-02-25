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


package org.graalvm.compiler.replacements.amd64;

// JaCoCo Exclude

import static org.graalvm.compiler.replacements.NodeIntrinsificationProvider.INJECTED_TARGET;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.Fold;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.core.common.SuppressFBWarnings;
import org.graalvm.compiler.replacements.nodes.BitScanForwardNode;
import org.graalvm.compiler.replacements.nodes.BitScanReverseNode;

import jdk.vm.ci.amd64.AMD64;
import jdk.vm.ci.code.TargetDescription;

@ClassSubstitution(Long.class)
public class AMD64LongSubstitutions {

    @Fold
    static boolean lzcnt(@Fold.InjectedParameter TargetDescription target) {
        AMD64 arch = (AMD64) target.arch;
        return arch.getFeatures().contains(AMD64.CPUFeature.LZCNT) && arch.getFlags().contains(AMD64.Flag.UseCountLeadingZerosInstruction);
    }

    @Fold
    static boolean tzcnt(@Fold.InjectedParameter TargetDescription target) {
        AMD64 arch = (AMD64) target.arch;
        return arch.getFeatures().contains(AMD64.CPUFeature.BMI1) && arch.getFlags().contains(AMD64.Flag.UseCountTrailingZerosInstruction);
    }

    @MethodSubstitution
    @SuppressFBWarnings(value = "NP_NULL_PARAM_DEREF_NONVIRTUAL", justification = "foldable method parameters are injected")
    public static int numberOfLeadingZeros(long i) {
        if (lzcnt(INJECTED_TARGET)) {
            return AMD64CountLeadingZerosNode.countLeadingZeros(i);
        }
        if (i == 0) {
            return 64;
        }
        return 63 - BitScanReverseNode.unsafeScan(i);
    }

    @MethodSubstitution
    @SuppressFBWarnings(value = "NP_NULL_PARAM_DEREF_NONVIRTUAL", justification = "foldable method parameters are injected")
    public static int numberOfTrailingZeros(long i) {
        if (tzcnt(INJECTED_TARGET)) {
            return AMD64CountTrailingZerosNode.countTrailingZeros(i);
        }

        if (i == 0) {
            return 64;
        }
        return BitScanForwardNode.unsafeScan(i);
    }
}

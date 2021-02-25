/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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

import java.lang.reflect.Array;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.nodes.DeoptimizeNode;
import org.graalvm.compiler.nodes.extended.BranchProbabilityNode;
import org.graalvm.compiler.nodes.java.ArrayLengthNode;

import jdk.vm.ci.meta.DeoptimizationAction;
import jdk.vm.ci.meta.DeoptimizationReason;

// JaCoCo Exclude

/**
 * Substitutions for {@link java.lang.reflect.Array} methods.
 */
@ClassSubstitution(Array.class)
public class ArraySubstitutions {

    @MethodSubstitution
    public static int getLength(Object array) {
        if (BranchProbabilityNode.probability(BranchProbabilityNode.DEOPT_PROBABILITY, !array.getClass().isArray())) {
            DeoptimizeNode.deopt(DeoptimizationAction.None, DeoptimizationReason.RuntimeConstraint);
        }
        return ArrayLengthNode.arrayLength(array);
    }

}

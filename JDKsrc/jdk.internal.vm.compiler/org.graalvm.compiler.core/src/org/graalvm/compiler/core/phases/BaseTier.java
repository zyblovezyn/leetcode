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


package org.graalvm.compiler.core.phases;

import static org.graalvm.compiler.core.common.GraalOptions.ImmutableCode;

import org.graalvm.compiler.loop.DefaultLoopPolicies;
import org.graalvm.compiler.loop.LoopPolicies;
import org.graalvm.compiler.options.OptionValues;
import org.graalvm.compiler.phases.PhaseSuite;
import org.graalvm.compiler.phases.common.CanonicalizerPhase;

public class BaseTier<C> extends PhaseSuite<C> {

    public LoopPolicies createLoopPolicies(@SuppressWarnings("unused") OptionValues options) {
        return new DefaultLoopPolicies();
    }

    public CanonicalizerPhase createCanonicalizerPhase(OptionValues options) {
        CanonicalizerPhase canonicalizer = null;
        if (ImmutableCode.getValue(options)) {
            canonicalizer = CanonicalizerPhase.createWithoutReadCanonicalization();
        } else {
            canonicalizer = CanonicalizerPhase.create();
        }
        return canonicalizer;
    }
}

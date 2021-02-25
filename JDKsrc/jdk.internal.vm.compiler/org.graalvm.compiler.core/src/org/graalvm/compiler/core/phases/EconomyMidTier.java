/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.nodes.spi.LoweringTool;
import org.graalvm.compiler.options.OptionValues;
import org.graalvm.compiler.phases.common.CanonicalizerPhase;
import org.graalvm.compiler.phases.common.FrameStateAssignmentPhase;
import org.graalvm.compiler.phases.common.GuardLoweringPhase;
import org.graalvm.compiler.phases.common.LoopSafepointInsertionPhase;
import org.graalvm.compiler.phases.common.LoweringPhase;
import org.graalvm.compiler.phases.common.RemoveValueProxyPhase;
import org.graalvm.compiler.phases.common.WriteBarrierAdditionPhase;
import org.graalvm.compiler.phases.tiers.MidTierContext;

public class EconomyMidTier extends BaseTier<MidTierContext> {

    public EconomyMidTier(OptionValues options) {
        CanonicalizerPhase canonicalizer = this.createCanonicalizerPhase(options);
        appendPhase(new RemoveValueProxyPhase());
        appendPhase(new LoopSafepointInsertionPhase());
        appendPhase(new GuardLoweringPhase());
        appendPhase(new LoweringPhase(canonicalizer, LoweringTool.StandardLoweringStage.MID_TIER));
        appendPhase(new FrameStateAssignmentPhase());
        appendPhase(new WriteBarrierAdditionPhase());
    }
}

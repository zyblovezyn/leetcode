/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.loop.phases;

import org.graalvm.compiler.loop.LoopPolicies;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.phases.BasePhase;

public abstract class LoopPhase<P extends LoopPolicies> extends BasePhase<CoreProviders> {
    private P policies;

    public LoopPhase(P policies) {
        this.policies = policies;
    }

    protected P getPolicies() {
        return policies;
    }
}

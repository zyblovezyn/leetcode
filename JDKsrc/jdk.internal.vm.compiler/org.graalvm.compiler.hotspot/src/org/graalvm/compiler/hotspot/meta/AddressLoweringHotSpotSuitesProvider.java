/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2017, Red Hat Inc. All rights reserved.
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


package org.graalvm.compiler.hotspot.meta;

import java.util.ListIterator;

import org.graalvm.compiler.hotspot.GraalHotSpotVMConfig;
import org.graalvm.compiler.hotspot.HotSpotGraalRuntimeProvider;
import org.graalvm.compiler.options.OptionValues;
import org.graalvm.compiler.phases.BasePhase;
import org.graalvm.compiler.phases.Phase;
import org.graalvm.compiler.phases.common.UseTrappingNullChecksPhase;
import org.graalvm.compiler.phases.schedule.SchedulePhase;
import org.graalvm.compiler.phases.tiers.LowTierContext;
import org.graalvm.compiler.phases.tiers.Suites;
import org.graalvm.compiler.phases.tiers.SuitesCreator;

/**
 * Subclass to factor out management of address lowering.
 */
public class AddressLoweringHotSpotSuitesProvider extends HotSpotSuitesProvider {

    private final Phase addressLowering;

    public AddressLoweringHotSpotSuitesProvider(SuitesCreator defaultSuitesCreator, GraalHotSpotVMConfig config, HotSpotGraalRuntimeProvider runtime,
                    Phase addressLowering) {
        super(defaultSuitesCreator, config, runtime);
        this.addressLowering = addressLowering;
    }

    @Override
    public Suites createSuites(OptionValues options) {
        Suites suites = super.createSuites(options);

        ListIterator<BasePhase<? super LowTierContext>> findPhase = suites.getLowTier().findPhase(UseTrappingNullChecksPhase.class);
        if (findPhase == null) {
            findPhase = suites.getLowTier().findPhase(SchedulePhase.class);
        }
        findPhase.previous();
        findPhase.add(addressLowering);

        return suites;
    }
}

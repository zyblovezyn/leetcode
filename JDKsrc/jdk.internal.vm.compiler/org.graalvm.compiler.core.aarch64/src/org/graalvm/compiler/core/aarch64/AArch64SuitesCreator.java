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


package org.graalvm.compiler.core.aarch64;

import java.util.List;
import java.util.ListIterator;

import org.graalvm.compiler.debug.GraalError;
import org.graalvm.compiler.java.DefaultSuitesCreator;
import org.graalvm.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration.Plugins;
import org.graalvm.compiler.options.OptionValues;
import org.graalvm.compiler.phases.BasePhase;
import org.graalvm.compiler.phases.Phase;
import org.graalvm.compiler.phases.PhaseSuite;
import org.graalvm.compiler.phases.tiers.CompilerConfiguration;
import org.graalvm.compiler.phases.tiers.LowTierContext;
import org.graalvm.compiler.phases.tiers.Suites;

public class AArch64SuitesCreator extends DefaultSuitesCreator {
    private final List<Class<? extends Phase>> insertReadReplacementBeforePositions;

    public AArch64SuitesCreator(CompilerConfiguration compilerConfiguration, Plugins plugins, List<Class<? extends Phase>> insertReadReplacementBeforePositions) {
        super(compilerConfiguration, plugins);
        this.insertReadReplacementBeforePositions = insertReadReplacementBeforePositions;
    }

    public AArch64SuitesCreator(CompilerConfiguration compilerConfiguration, List<Class<? extends Phase>> insertReadReplacementBeforePositions) {
        super(compilerConfiguration);
        this.insertReadReplacementBeforePositions = insertReadReplacementBeforePositions;
    }

    @Override
    public Suites createSuites(OptionValues options) {
        Suites suites = super.createSuites(options);
        ListIterator<BasePhase<? super LowTierContext>> findPhase = null;
        for (Class<? extends Phase> phase : insertReadReplacementBeforePositions) {
            findPhase = suites.getLowTier().findPhase(phase);
            if (findPhase != null) {
                // Put AArch64ReadReplacementPhase right before the requested phase
                while (PhaseSuite.findNextPhase(findPhase, phase)) {
                    // Search for last occurrence of SchedulePhase
                }
                findPhase.previous();
                break;
            }
        }
        if (findPhase != null) {
            findPhase.add(new AArch64ReadReplacementPhase());
        } else {
            throw GraalError.shouldNotReachHere("Cannot find phase to insert AArch64ReadReplacementPhase");
        }
        return suites;
    }
}

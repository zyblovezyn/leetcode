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


package org.graalvm.compiler.nodes.spi;

import org.graalvm.compiler.core.common.spi.ConstantFieldProvider;
import org.graalvm.compiler.core.common.spi.ForeignCallsProvider;
import org.graalvm.compiler.core.common.spi.MetaAccessExtensionProvider;

import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.MetaAccessProvider;

public class CoreProvidersImpl implements CoreProviders {
    protected final MetaAccessProvider metaAccess;
    protected final ConstantReflectionProvider constantReflection;
    protected final ConstantFieldProvider constantFieldProvider;
    protected final LoweringProvider lowerer;
    protected final Replacements replacements;
    protected final StampProvider stampProvider;
    protected final ForeignCallsProvider foreignCalls;
    protected final PlatformConfigurationProvider platformConfigurationProvider;
    protected final MetaAccessExtensionProvider metaAccessExtensionProvider;

    protected CoreProvidersImpl(MetaAccessProvider metaAccess, ConstantReflectionProvider constantReflection, ConstantFieldProvider constantFieldProvider, LoweringProvider lowerer,
                    Replacements replacements, StampProvider stampProvider, ForeignCallsProvider foreignCalls, PlatformConfigurationProvider platformConfigurationProvider,
                    MetaAccessExtensionProvider metaAccessExtensionProvider) {
        this.metaAccess = metaAccess;
        this.constantReflection = constantReflection;
        this.constantFieldProvider = constantFieldProvider;
        this.lowerer = lowerer;
        this.replacements = replacements;
        this.stampProvider = stampProvider;
        this.foreignCalls = foreignCalls;
        this.platformConfigurationProvider = platformConfigurationProvider;
        this.metaAccessExtensionProvider = metaAccessExtensionProvider;
    }

    @Override
    public MetaAccessProvider getMetaAccess() {
        return metaAccess;
    }

    @Override
    public ConstantReflectionProvider getConstantReflection() {
        return constantReflection;
    }

    @Override
    public ConstantFieldProvider getConstantFieldProvider() {
        return constantFieldProvider;
    }

    @Override
    public LoweringProvider getLowerer() {
        return lowerer;
    }

    @Override
    public Replacements getReplacements() {
        return replacements;
    }

    @Override
    public StampProvider getStampProvider() {
        return stampProvider;
    }

    @Override
    public ForeignCallsProvider getForeignCalls() {
        return foreignCalls;
    }

    @Override
    public PlatformConfigurationProvider getPlatformConfigurationProvider() {
        return platformConfigurationProvider;
    }

    @Override
    public MetaAccessExtensionProvider getMetaAccessExtensionProvider() {
        return metaAccessExtensionProvider;
    }
}

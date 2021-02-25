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

public class CoreProvidersDelegate implements CoreProviders {

    private final CoreProviders providers;

    protected CoreProvidersDelegate(CoreProviders providers) {
        this.providers = providers;
    }

    public CoreProviders getProviders() {
        return providers;
    }

    @Override
    public MetaAccessProvider getMetaAccess() {
        return providers.getMetaAccess();
    }

    @Override
    public ConstantReflectionProvider getConstantReflection() {
        return providers.getConstantReflection();
    }

    @Override
    public ConstantFieldProvider getConstantFieldProvider() {
        return providers.getConstantFieldProvider();
    }

    @Override
    public LoweringProvider getLowerer() {
        return providers.getLowerer();
    }

    @Override
    public Replacements getReplacements() {
        return providers.getReplacements();
    }

    @Override
    public StampProvider getStampProvider() {
        return providers.getStampProvider();
    }

    @Override
    public ForeignCallsProvider getForeignCalls() {
        return providers.getForeignCalls();
    }

    @Override
    public PlatformConfigurationProvider getPlatformConfigurationProvider() {
        return providers.getPlatformConfigurationProvider();
    }

    @Override
    public MetaAccessExtensionProvider getMetaAccessExtensionProvider() {
        return providers.getMetaAccessExtensionProvider();
    }
}

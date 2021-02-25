/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.phases.util;

import org.graalvm.compiler.core.common.spi.CodeGenProviders;
import org.graalvm.compiler.core.common.spi.ConstantFieldProvider;
import org.graalvm.compiler.core.common.spi.ForeignCallsProvider;
import org.graalvm.compiler.core.common.spi.MetaAccessExtensionProvider;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.nodes.spi.CoreProvidersImpl;
import org.graalvm.compiler.nodes.spi.LoweringProvider;
import org.graalvm.compiler.nodes.spi.PlatformConfigurationProvider;
import org.graalvm.compiler.nodes.spi.Replacements;
import org.graalvm.compiler.nodes.spi.StampProvider;

import jdk.vm.ci.code.CodeCacheProvider;
import jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.vm.ci.meta.MetaAccessProvider;

/**
 * A set of providers, some of which may not be present (i.e., null).
 */
public class Providers extends CoreProvidersImpl implements CodeGenProviders {

    private final CodeCacheProvider codeCache;

    public Providers(MetaAccessProvider metaAccess, CodeCacheProvider codeCache, ConstantReflectionProvider constantReflection, ConstantFieldProvider constantFieldProvider,
                    ForeignCallsProvider foreignCalls, LoweringProvider lowerer, Replacements replacements, StampProvider stampProvider, PlatformConfigurationProvider platformConfigurationProvider,
                    MetaAccessExtensionProvider metaAccessExtensionProvider) {
        super(metaAccess, constantReflection, constantFieldProvider, lowerer, replacements, stampProvider, foreignCalls, platformConfigurationProvider, metaAccessExtensionProvider);
        this.codeCache = codeCache;
    }

    public Providers(Providers copyFrom) {
        this(copyFrom.getMetaAccess(), copyFrom.getCodeCache(), copyFrom.getConstantReflection(), copyFrom.getConstantFieldProvider(), copyFrom.getForeignCalls(), copyFrom.getLowerer(),
                        copyFrom.getReplacements(), copyFrom.getStampProvider(), copyFrom.getPlatformConfigurationProvider(), copyFrom.getMetaAccessExtensionProvider());
    }

    public Providers(CoreProviders copyFrom) {
        this(copyFrom.getMetaAccess(), null, copyFrom.getConstantReflection(), copyFrom.getConstantFieldProvider(), null, copyFrom.getLowerer(), copyFrom.getReplacements(),
                        copyFrom.getStampProvider(), copyFrom.getPlatformConfigurationProvider(), copyFrom.getMetaAccessExtensionProvider());
    }

    @Override
    public CodeCacheProvider getCodeCache() {
        return codeCache;
    }

    public Providers copyWith(ConstantReflectionProvider substitution) {
        assert this.getClass() == Providers.class : "must override in " + getClass();
        return new Providers(metaAccess, codeCache, substitution, constantFieldProvider, foreignCalls, lowerer, replacements, stampProvider, platformConfigurationProvider,
                        metaAccessExtensionProvider);
    }

    public Providers copyWith(ConstantFieldProvider substitution) {
        assert this.getClass() == Providers.class : "must override in " + getClass();
        return new Providers(metaAccess, codeCache, constantReflection, substitution, foreignCalls, lowerer, replacements, stampProvider, platformConfigurationProvider, metaAccessExtensionProvider);
    }

    public Providers copyWith(Replacements substitution) {
        assert this.getClass() == Providers.class : "must override in " + getClass();
        return new Providers(metaAccess, codeCache, constantReflection, constantFieldProvider, foreignCalls, lowerer, substitution, stampProvider, platformConfigurationProvider,
                        metaAccessExtensionProvider);
    }
}

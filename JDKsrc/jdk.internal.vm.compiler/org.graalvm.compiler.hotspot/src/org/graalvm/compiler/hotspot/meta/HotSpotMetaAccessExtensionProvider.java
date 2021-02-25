/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.core.common.spi.MetaAccessExtensionProvider;

import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.JavaType;
import jdk.vm.ci.meta.ResolvedJavaType;

public class HotSpotMetaAccessExtensionProvider implements MetaAccessExtensionProvider {
    @Override
    public JavaKind getStorageKind(JavaType type) {
        return type.getJavaKind();
    }

    @Override
    public boolean canConstantFoldDynamicAllocation(ResolvedJavaType type) {
        /*
         * The HotSpot lowering of DynamicNewInstanceNode includes an explicit is-initialized check
         * and deoptimizes, but the lowering of NewInstanceNode does not. So we must not constant
         * fold a non-initialized instance allocation.
         */
        return type.isArray() || type.isInitialized();
    }
}

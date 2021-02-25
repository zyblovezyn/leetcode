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


package org.graalvm.compiler.core.common.spi;

import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.JavaType;
import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * Provides additional meta data about JVMCI objects that is not provided by the VM itself, and
 * therefore does not need to be in JVMCI itself.
 */
public interface MetaAccessExtensionProvider {

    /**
     * The {@link JavaKind} used to store the provided type in a field or array element. This can be
     * different than the {@link JavaType#getJavaKind} for types that are intercepted and
     * transformed by the compiler.
     */
    JavaKind getStorageKind(JavaType type);

    /**
     * Checks if a dynamic allocation of the provided type can be canonicalized to a regular
     * allocation node. If the method returns false, then the dynamic allocation would throw an
     * exception at run time and therefore canonicalization would miss that exception.
     */
    boolean canConstantFoldDynamicAllocation(ResolvedJavaType type);
}

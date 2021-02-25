/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core.common.type;

import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * This interface represents an object which contains a symbolic reference to a JVMCI type or method
 * that can be converted back into the original object by looking up types relative to an
 * {@code accessingClass}.
 */
public interface SymbolicJVMCIReference<T> {
    T resolve(ResolvedJavaType accessingClass);
}

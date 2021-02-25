/*
 * Copyright (c) 2009, 2020, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Arrays;

/**
 * The name and signature of a {@link ForeignCallDescriptor foreign call}.
 */
public final class ForeignCallSignature {

    private final String name;
    private final Class<?> resultType;
    private final Class<?>[] argumentTypes;

    public ForeignCallSignature(String name, Class<?> resultType, Class<?>... argumentTypes) {
        this.name = name;
        this.resultType = resultType;
        this.argumentTypes = argumentTypes;
    }

    /**
     * Gets the name of this foreign call.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the return type of this foreign call.
     */
    public Class<?> getResultType() {
        return resultType;
    }

    /**
     * Gets the argument types of this foreign call.
     */
    public Class<?>[] getArgumentTypes() {
        return argumentTypes.clone();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ForeignCallSignature) {
            ForeignCallSignature other = (ForeignCallSignature) obj;
            return other.name.equals(name) && other.resultType.equals(resultType) && Arrays.equals(other.argumentTypes, argumentTypes);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name).append('(');
        String sep = "";
        for (Class<?> arg : argumentTypes) {
            sb.append(sep).append(arg.getSimpleName());
            sep = ",";
        }
        return sb.append(')').append(resultType.getSimpleName()).toString();
    }
}

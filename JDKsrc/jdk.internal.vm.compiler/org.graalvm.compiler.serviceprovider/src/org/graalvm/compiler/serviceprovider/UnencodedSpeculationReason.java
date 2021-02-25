/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.serviceprovider;

import java.util.Arrays;

import jdk.vm.ci.meta.SpeculationLog.SpeculationReason;

/**
 * An implementation of {@link SpeculationReason} based on direct, unencoded values.
 */
public final class UnencodedSpeculationReason implements SpeculationReason {
    final int groupId;
    final String groupName;
    final Object[] context;

    UnencodedSpeculationReason(int groupId, String groupName, Object[] context) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.context = context;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnencodedSpeculationReason) {
            UnencodedSpeculationReason that = (UnencodedSpeculationReason) obj;
            return this.groupId == that.groupId && Arrays.equals(this.context, that.context);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return groupId + Arrays.hashCode(this.context);
    }

    @Override
    public String toString() {
        return String.format("%s@%d%s", groupName, groupId, Arrays.toString(context));
    }
}

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


package org.graalvm.compiler.core.common.spi;

import org.graalvm.compiler.core.common.LIRKind;

import jdk.vm.ci.code.ValueKindFactory;

/**
 * Details about a set of supported {@link ForeignCallDescriptor foreign calls}.
 */
public interface ForeignCallsProvider extends ValueKindFactory<LIRKind> {

    /**
     * Gets the linkage for a foreign call.
     */
    ForeignCallLinkage lookupForeignCall(ForeignCallDescriptor descriptor);

    /**
     * Gets the linkage for a foreign call.
     */
    default ForeignCallLinkage lookupForeignCall(ForeignCallSignature signature) {
        return lookupForeignCall(getDescriptor(signature));
    }

    /**
     * Gets the descriptor for a foreign call.
     */
    ForeignCallDescriptor getDescriptor(ForeignCallSignature signature);
}

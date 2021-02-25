/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot;

import org.graalvm.compiler.core.common.spi.ForeignCallLinkage;
import org.graalvm.compiler.core.target.Backend;
import org.graalvm.compiler.hotspot.meta.HotSpotForeignCallDescriptor;
import org.graalvm.compiler.hotspot.stubs.Stub;

import jdk.vm.ci.meta.InvokeTarget;

/**
 * The details required to link a HotSpot runtime or stub call.
 */
public interface HotSpotForeignCallLinkage extends ForeignCallLinkage, InvokeTarget {

    /**
     * Constants for specifying whether a foreign call destroys or preserves registers. A foreign
     * call will always destroy {@link HotSpotForeignCallLinkage#getOutgoingCallingConvention() its}
     * {@linkplain ForeignCallLinkage#getTemporaries() temporary} registers.
     */
    enum RegisterEffect {
        DESTROYS_ALL_CALLER_SAVE_REGISTERS,
        COMPUTES_REGISTERS_KILLED
    }

    @Override
    HotSpotForeignCallDescriptor getDescriptor();

    /**
     * Sentinel marker for a computed jump address.
     */
    long JUMP_ADDRESS = 0xDEADDEADBEEFBEEFL;

    void setCompiledStub(Stub stub);

    RegisterEffect getEffect();

    /**
     * Determines if this is a call to a compiled {@linkplain Stub stub}.
     */
    boolean isCompiledStub();

    /**
     * Gets the stub, if any, this foreign call links to.
     */
    Stub getStub();

    void finalizeAddress(Backend backend);

    long getAddress();

    /**
     * Determines if the runtime function or stub might use floating point registers. If the answer
     * is no, then no FPU state management prologue or epilogue needs to be emitted around the call.
     */
    boolean mayContainFP();

    /**
     * Determines if a {@code JavaFrameAnchor} needs to be set up and torn down around this call.
     */
    boolean needsJavaFrameAnchor();

    /**
     * Gets the VM symbol associated with the target {@linkplain #getAddress() address} of the call.
     */
    String getSymbol();
}

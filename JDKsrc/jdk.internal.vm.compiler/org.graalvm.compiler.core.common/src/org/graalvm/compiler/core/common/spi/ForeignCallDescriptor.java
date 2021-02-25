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

import jdk.internal.vm.compiler.word.LocationIdentity;

/**
 * The information required for high level code generation of a foreign call. A foreign call differs
 * from a normal compiled Java call in at least one of these aspects:
 * <ul>
 * <li>The call is to C/C++/assembler code.</li>
 * <li>The call uses different conventions for passing parameters or returning values.</li>
 * <li>The callee has different register saving semantics. For example, the callee may save all
 * registers (apart from some specified temporaries) in which case the register allocator doesn't
 * not need to spill all live registers around the call site.</li>
 * <li>The call does not occur at an INVOKE* bytecode. Such a call could be transformed into a
 * standard Java call if the foreign routine is a normal Java method and the runtime supports
 * linking Java calls at arbitrary bytecodes.</li>
 * </ul>
 */
public class ForeignCallDescriptor {

    protected final ForeignCallSignature signature;
    protected final boolean isReexecutable;
    protected final boolean canDeoptimize;
    protected final boolean isGuaranteedSafepoint;
    protected final LocationIdentity[] killedLocations;

    public ForeignCallDescriptor(String name, Class<?> resultType, Class<?>[] argumentTypes, boolean isReexecutable, LocationIdentity[] killedLocations, boolean canDeoptimize,
                    boolean isGuaranteedSafepoint) {
        this.isReexecutable = isReexecutable;
        this.killedLocations = killedLocations;
        this.canDeoptimize = canDeoptimize;
        this.isGuaranteedSafepoint = isGuaranteedSafepoint;
        this.signature = new ForeignCallSignature(name, resultType, argumentTypes);

    }

    public ForeignCallSignature getSignature() {
        return signature;
    }

    public String getName() {
        return signature.getName();
    }

    public Class<?> getResultType() {
        return signature.getResultType();
    }

    public Class<?>[] getArgumentTypes() {
        return signature.getArgumentTypes();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return signature.hashCode();
    }

    /**
     * Determines if a given foreign call is side-effect free. Deoptimization cannot return
     * execution to a point before a foreign call that has a side effect.
     */
    public boolean isReexecutable() {
        return isReexecutable;
    }

    /**
     * Gets the set of memory locations killed by a given foreign call. Returning the special value
     * {@link LocationIdentity#any()} denotes that the call kills all memory locations. Returning
     * any empty array denotes that the call does not kill any memory locations.
     */
    public LocationIdentity[] getKilledLocations() {
        return killedLocations;
    }

    /**
     * Determines if deoptimization can occur during a given foreign call.
     */
    public boolean canDeoptimize() {
        return canDeoptimize;
    }

    /**
     * Identifies foreign calls which are guaranteed to include a safepoint check.
     */
    public boolean isGuaranteedSafepoint() {
        return isGuaranteedSafepoint;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + signature +
                        ", isReexecutable=" + isReexecutable +
                        ", canDeoptimize=" + canDeoptimize +
                        ", isGuaranteedSafepoint=" + isGuaranteedSafepoint +
                        ", killedLocations=" + Arrays.toString(killedLocations) +
                        '}';
    }
}

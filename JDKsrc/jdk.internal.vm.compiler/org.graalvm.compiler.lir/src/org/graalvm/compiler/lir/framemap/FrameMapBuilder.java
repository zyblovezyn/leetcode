/*
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.lir.framemap;

import org.graalvm.compiler.lir.VirtualStackSlot;
import org.graalvm.compiler.lir.gen.LIRGenerationResult;

import jdk.vm.ci.code.CallingConvention;
import jdk.vm.ci.code.CodeCacheProvider;
import jdk.vm.ci.code.RegisterConfig;
import jdk.vm.ci.meta.ValueKind;

/**
 * A {@link FrameMapBuilder} is used to collect all information necessary to
 * {@linkplain #buildFrameMap create} a {@link FrameMap}.
 */
public abstract class FrameMapBuilder {

    /**
     * Reserves a spill slot in the frame of the method being compiled. The returned slot is aligned
     * on its natural alignment, i.e., an 8-byte spill slot is aligned at an 8-byte boundary, unless
     * overridden by a subclass.
     *
     * @param kind The kind of the spill slot to be reserved.
     * @return A spill slot denoting the reserved memory area.
     */
    public abstract VirtualStackSlot allocateSpillSlot(ValueKind<?> kind);

    /**
     * Reserves a number of contiguous slots in the frame of the method being compiled. If the
     * requested number of slots is 0, this method returns {@code null}.
     *
     * @param slots the number of slots to reserve
     * @return the first reserved stack slot (i.e., at the lowest address)
     */
    public abstract VirtualStackSlot allocateStackSlots(int slots);

    public abstract RegisterConfig getRegisterConfig();

    public abstract CodeCacheProvider getCodeCache();

    /**
     * Informs the frame map that the compiled code calls a particular method, which may need stack
     * space for outgoing arguments.
     *
     * @param cc The calling convention for the called method.
     */
    public abstract void callsMethod(CallingConvention cc);

    /**
     * Creates a {@linkplain FrameMap} based on the information collected by this
     * {@linkplain FrameMapBuilder}.
     */
    public abstract FrameMap buildFrameMap(LIRGenerationResult result);
}

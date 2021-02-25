/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.replacements;

import static org.graalvm.compiler.hotspot.GraalHotSpotVMConfig.INJECTED_VMCONFIG;
import static org.graalvm.compiler.hotspot.replacements.HotSpotReplacementsUtil.JAVA_THREAD_OSTHREAD_LOCATION;
import static org.graalvm.compiler.hotspot.replacements.HotSpotReplacementsUtil.JAVA_THREAD_THREAD_OBJECT_LOCATION;
import static org.graalvm.compiler.hotspot.replacements.HotSpotReplacementsUtil.osThreadInterruptedOffset;
import static org.graalvm.compiler.hotspot.replacements.HotSpotReplacementsUtil.osThreadOffset;
import static org.graalvm.compiler.hotspot.replacements.HotSpotReplacementsUtil.threadObjectOffset;
import static jdk.internal.vm.compiler.word.LocationIdentity.any;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.hotspot.nodes.CurrentJavaThreadNode;
import org.graalvm.compiler.word.Word;

/**
 * Substitutions for {@link java.lang.Thread} methods.
 */
@ClassSubstitution(Thread.class)
public class ThreadSubstitutions {

    /**
     * hidden in 9.
     */
    @MethodSubstitution(isStatic = false, optional = true)
    public static boolean isInterrupted(final Thread thisObject, boolean clearInterrupted) {
        Word javaThread = CurrentJavaThreadNode.get();
        Object thread = javaThread.readObject(threadObjectOffset(INJECTED_VMCONFIG), JAVA_THREAD_THREAD_OBJECT_LOCATION);
        if (thisObject == thread) {
            Word osThread = javaThread.readWord(osThreadOffset(INJECTED_VMCONFIG), JAVA_THREAD_OSTHREAD_LOCATION);
            boolean interrupted = osThread.readInt(osThreadInterruptedOffset(INJECTED_VMCONFIG), any()) != 0;
            if (!interrupted || !clearInterrupted) {
                return interrupted;
            }
        }
        return isInterrupted(thisObject, clearInterrupted);
    }
}

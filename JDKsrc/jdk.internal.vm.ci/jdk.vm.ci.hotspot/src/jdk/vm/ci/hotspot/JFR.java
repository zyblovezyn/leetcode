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


package jdk.vm.ci.hotspot;

import static jdk.vm.ci.hotspot.CompilerToVM.compilerToVM;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * Helper methods for interacting with the Java Flight Recorder (JFR) to register events and notify it when events occur.
 * The JFR events are defined in {see @code src/share/jfr/metadata/metadata.xml}.
 */
public final class JFR {

    /**
     * Provides access to current JFR time stamp.
     */
    public static final class Ticks {

        /**
         * @return current JFR time stamp
         */
        public static long now() {
            return compilerToVM().ticksNow();
        }
    }

    /**
     * Helper methods for managing JFR CompilerPhase events.
     * The events are defined in {see @code src/share/jfr/metadata/metadata.xml}.
     */
    public static final class CompilerPhaseEvent {

        private static final ConcurrentHashMap<String, Integer> phaseToId = new ConcurrentHashMap<>();

        private static int getPhaseToId(String phaseName) {
            String[] phaseNames = { phaseName };
            return phaseToId.computeIfAbsent(phaseName, k -> compilerToVM().registerCompilerPhases(phaseNames));
        }

        /**
         * Registers new compiler phases with JFR. This should be called during compiler initialization.
         *
         * @param phaseNames compiler phase names
         */
        public static synchronized void registerPhases(String[] phaseNames) {
            ArrayList<String> toProcess = new ArrayList<>(Arrays.asList(phaseNames));
            toProcess.removeAll(phaseToId.keySet());
            int pid = compilerToVM().registerCompilerPhases(toProcess.toArray(new String[toProcess.size()]));
            for (String phase : toProcess) {
                phaseToId.put(phase, pid++);
            }
        }

        /**
         * Commits a CompilerPhase event.
         *
         * @param startTime time captured at the start of compiler phase
         * @param phaseName compiler phase name
         * @param compileId current compilation unit id
         * @param phaseLevel compiler phase nesting level
         */
        public static void write(long startTime, String phaseName, int compileId, int phaseLevel) {
            compilerToVM().notifyCompilerPhaseEvent(startTime, getPhaseToId(phaseName), compileId, phaseLevel);
        }
    }

    /**
     * Helper methods for managing JFR CompilerInlining events.
     * The events are defined in {see @code src/share/jfr/metadata/metadata.xml}.
     */
    public static final class CompilerInliningEvent {

        /**
         * Commits a CompilerInlining event.
         *
         * @param compileId current compilation unit id
         * @param caller caller method
         * @param callee callee method
         * @param succeeded inlining succeeded or not
         * @param message extra information on inlining
         * @param bci invocation byte code index
         */
        public static void write(int compileId, ResolvedJavaMethod caller, ResolvedJavaMethod callee, boolean succeeded, String message, int bci) {
            compilerToVM().notifyCompilerInliningEvent(compileId, (HotSpotResolvedJavaMethodImpl) caller, (HotSpotResolvedJavaMethodImpl) callee, succeeded, message, bci);
        }
    }
}


/*
 * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.microbenchmarks.lir;

import org.graalvm.compiler.lir.gen.LIRGenerationResult;
import org.graalvm.compiler.microbenchmarks.graal.GraalBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

public class RegisterAllocationTimeBenchmark extends GraalBenchmark {

    public static class State extends GraalCompilerState.AllocationStage {
        @MethodDescString @Param({
                        "java.lang.String#equals",
                        "java.util.HashMap#computeIfAbsent"
        }) public String method;
    }

    @Benchmark
    public LIRGenerationResult allocateRegisters(State s) {
        return s.compile();
    }
}

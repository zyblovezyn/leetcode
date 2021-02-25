/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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


package micro.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;

/**
 * Benchmarks cost of ArrayList.
 */
public class ArrayAllocationBenchmark extends BenchmarkBase {

    @State(Scope.Benchmark)
    public static class ThreadState {
        @Param({"128", "1024", "65536"}) int size;
        byte[] result;
    }

    @Benchmark
    @Threads(Threads.MAX)
    public void arrayAllocate(ThreadState state) {
        state.result = new byte[state.size];
    }
}

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


package micro.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class ObjectCloneArrayLengthBenchmark extends BenchmarkBase {

    static class A {
        final int x;

        A(int x) {
            this.x = x;
        }
    }

    @State(Scope.Benchmark)
    public static class ThreadState {
        int length = 10;
        A[] array = new A[]{new A(1), new A(2), new A(3), new A(4), new A(5)};
    }

    @Benchmark
    public int arrayAllocLength(ThreadState t) {
        return new int[t.length].length;
    }

    @Benchmark
    public int arrayAllocCloneLength(ThreadState t) {
        return new int[t.length].clone().length;
    }

    @Benchmark
    public int arrayLength(ThreadState t) {
        return t.array.length;
    }

    @Benchmark
    public int arrayCloneLength(ThreadState t) {
        return t.array.clone().length;
    }
}

/*
 * Copyright (c) 2015, 2020, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Benchmarks cost of non-contended synchronization.
 */
public class SimpleSyncBenchmark extends BenchmarkBase {

    public static class Person {
        public int age;

        public Person(int age) {
            this.age = age;
        }

        public synchronized int getAge() {
            return age;
        }

        public synchronized void setAge(int age) {
            this.age = age;
        }

        public synchronized void setAgeIfNonZero(int age) {
            if (age != 0) {
                this.age = age;
            }
        }
    }

    @State(Scope.Benchmark)
    public static class ThreadState {
        Person person = new Person(22);
        int newAge = 45;
    }

    @Benchmark
    public void setAgeCond(ThreadState state) {
        Person person = state.person;
        person.setAgeIfNonZero(state.newAge);
    }

    @Benchmark
    public int getAge(ThreadState state) {
        Person person = state.person;
        return person.getAge();
    }

    @Benchmark
    public int getAndIncAge(ThreadState state) {
        Person person = state.person;
        int oldAge = person.getAge();
        person.setAge(oldAge + 1);
        return oldAge;
    }
}

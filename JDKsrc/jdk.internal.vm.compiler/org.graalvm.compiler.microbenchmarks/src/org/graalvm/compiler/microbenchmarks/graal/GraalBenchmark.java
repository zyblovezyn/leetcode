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


package org.graalvm.compiler.microbenchmarks.graal;

import org.graalvm.compiler.api.test.ModuleSupport;

import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;

/**
 * All classes defining Graal benchmarks must subclass this class as it defines the default value
 * for each benchmark option. Individual options can be overridden in the subclasses or by an
 * individual benchmark.
 */
@Warmup(iterations = 8, time = 5)
@Measurement(iterations = 5, time = 5)
@Fork(3)
public class GraalBenchmark {
    static {
        ModuleSupport.exportAndOpenAllPackagesToUnnamed("jdk.internal.vm.compiler");
    }
}

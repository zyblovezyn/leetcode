/*
 * Copyright (c) 2017, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.graph.test;

import org.graalvm.compiler.api.test.Graal;
import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.debug.DebugContext.Builder;
import org.graalvm.compiler.options.OptionValues;
import org.junit.After;

public abstract class GraphTest {

    static OptionValues getOptions() {
        return Graal.getRequiredCapability(OptionValues.class);
    }

    private final ThreadLocal<DebugContext> cachedDebug = new ThreadLocal<>();

    protected DebugContext getDebug(OptionValues options) {
        DebugContext cached = cachedDebug.get();
        if (cached != null) {
            if (cached.getOptions() == options) {
                return cached;
            }
            throw new AssertionError("At most one " + DebugContext.class.getName() + " object should be created per test");
        }
        DebugContext debug = new Builder(options).build();
        cachedDebug.set(debug);
        return debug;
    }

    protected DebugContext getDebug() {
        return getDebug(getOptions());
    }

    @After
    public void afterTest() {
        DebugContext cached = cachedDebug.get();
        if (cached != null) {
            cached.closeDumpHandlers(true);
        }
    }
}

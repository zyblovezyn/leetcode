/*
 * Copyright (c) 2011, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core.test.deopt;

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.core.test.ea.EATestBase.TestClassObject;
import org.graalvm.compiler.serviceprovider.JavaVersionUtil;
import org.junit.Assume;
import org.junit.Test;

/**
 * In the following tests, we try to deoptimize out of synchronized methods.
 */
public class SynchronizedMethodDeoptimizationTest extends GraalCompilerTest {

    public static final TestClassObject testObject = null;

    public static synchronized Object testMethodSynchronized(Object o) {
        if (o == null) {
            // this branch will always deoptimize
            return testObject.x;
        }
        return o;
    }

    @Test
    public void test1() {
        // https://bugs.openjdk.java.net/browse/JDK-8182755
        Assume.assumeTrue(JavaVersionUtil.JAVA_SPEC <= 8);

        test("testMethodSynchronized", "test");
        test("testMethodSynchronized", (Object) null);
    }
}

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


package org.graalvm.compiler.hotspot.test;

import static org.junit.Assume.assumeTrue;

import java.util.Base64;

import org.graalvm.compiler.api.test.Graal;
import org.graalvm.compiler.hotspot.HotSpotGraalRuntimeProvider;
import org.graalvm.compiler.runtime.RuntimeProvider;
import org.junit.Before;
import org.junit.Test;

public class HotSpotBase64Test extends HotSpotGraalCompilerTest {

    // Checkstyle: stop
    private static final String lipsum = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata ";
    // Checkstyle: resume

    @Before
    public void sanity() {
        HotSpotGraalRuntimeProvider rt = (HotSpotGraalRuntimeProvider) Graal.getRequiredCapability(RuntimeProvider.class);
        assumeTrue("Enable test case when the hotspot intrinsic is available", rt.getVMConfig().useBase64Intrinsics());
    }

    @Test
    public void testEncode() {
        test(getResolvedJavaMethod(Base64.Encoder.class, "encode", byte[].class), Base64.getEncoder(), lipsum.getBytes());
    }

}

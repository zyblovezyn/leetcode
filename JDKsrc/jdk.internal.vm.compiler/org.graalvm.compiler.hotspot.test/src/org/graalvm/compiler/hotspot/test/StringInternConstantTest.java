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

import org.graalvm.compiler.core.test.GraalCompilerTest;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.FixedNode;
import org.graalvm.compiler.nodes.ReturnNode;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.junit.Test;

import jdk.vm.ci.hotspot.HotSpotObjectConstant;
import jdk.vm.ci.meta.Constant;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Assert;

/**
 * Tests constant folding of {@link String#intern()}.
 */
public class StringInternConstantTest extends GraalCompilerTest {

    private static final String A_CONSTANT_STRING = "a constant string";

    @Test
    public void test1() {
        ResolvedJavaMethod method = getResolvedJavaMethod("constantIntern");
        StructuredGraph graph = parseForCompile(method);

        FixedNode firstFixed = graph.start().next();
        Assert.assertThat(firstFixed, instanceOf(ReturnNode.class));

        ReturnNode ret = (ReturnNode) firstFixed;
        if (ret.result() instanceof ConstantNode) {
            String expected = A_CONSTANT_STRING.intern();
            Constant constant = ((ConstantNode) ret.result()).getValue();
            if (constant instanceof HotSpotObjectConstant) {
                String returnedString = ((HotSpotObjectConstant) constant).asObject(String.class);
                Assert.assertSame("result", expected, returnedString);
            } else {
                Assert.fail("expected HotSpotObjectConstant, got: " + constant.getClass());
            }
        } else {
            Assert.fail("result not constant: " + ret.result());
        }
    }

    public static String constantIntern() {
        return A_CONSTANT_STRING.intern();
    }
}

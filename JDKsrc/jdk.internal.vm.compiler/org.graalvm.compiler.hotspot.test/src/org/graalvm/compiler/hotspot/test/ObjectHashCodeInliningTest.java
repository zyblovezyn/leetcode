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
import org.graalvm.compiler.java.BytecodeParserOptions;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.extended.ForeignCallNode;
import org.graalvm.compiler.nodes.java.MethodCallTargetNode;
import org.graalvm.compiler.nodes.memory.ReadNode;
import org.graalvm.compiler.options.OptionValues;
import org.junit.Test;

import jdk.vm.ci.meta.JavaTypeProfile;
import jdk.vm.ci.meta.JavaTypeProfile.ProfiledType;
import jdk.vm.ci.meta.MetaAccessProvider;
import jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.vm.ci.meta.TriState;

public class ObjectHashCodeInliningTest extends GraalCompilerTest {

    public static int getHash(Object obj) {
        return obj.hashCode();
    }

    @Test
    public void testGetHash() {
        MetaAccessProvider metaAccess = getMetaAccess();
        ProfiledType[] injectedProfile = {
                        new ProfiledType(metaAccess.lookupJavaType(String.class), 0.9D),
                        new ProfiledType(metaAccess.lookupJavaType(Object.class), 0.1D)};

        ResolvedJavaMethod method = getResolvedJavaMethod("getHash");
        StructuredGraph graph = parseForCompile(method,
                        new OptionValues(getInitialOptions(), BytecodeParserOptions.InlineDuringParsing, false, BytecodeParserOptions.InlineIntrinsicsDuringParsing, false));
        for (MethodCallTargetNode callTargetNode : graph.getNodes(MethodCallTargetNode.TYPE)) {
            if ("Object.hashCode".equals(callTargetNode.targetName())) {
                callTargetNode.setJavaTypeProfile(new JavaTypeProfile(TriState.FALSE, 0.0D, injectedProfile));
            }
        }

        compile(method, graph);
    }

    private static boolean containsForeignCallToIdentityHashCode(StructuredGraph graph) {
        for (ForeignCallNode foreignCallNode : graph.getNodes().filter(ForeignCallNode.class)) {
            if ("identity_hashcode".equals(foreignCallNode.getDescriptor().getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsReadStringHash(StructuredGraph graph) {
        for (ReadNode readNode : graph.getNodes().filter(ReadNode.class)) {
            if ("String.hash".equals(readNode.getLocationIdentity().toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void checkHighTierGraph(StructuredGraph graph) {
        assert containsForeignCallToIdentityHashCode(graph) : "expected a foreign call to identity_hashcode";
        assert containsReadStringHash(graph) : "expected a read from String.hash";
    }

}

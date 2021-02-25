/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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
 *
 *
 */

package org.graalvm.compiler.core.test;

import jdk.vm.ci.meta.ResolvedJavaType;
import org.graalvm.compiler.nodes.CallTargetNode;
import org.graalvm.compiler.nodes.InvokeNode;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.java.InstanceOfNode;
import org.graalvm.compiler.phases.OptimisticOptimizations;
import org.graalvm.compiler.phases.tiers.HighTierContext;
import org.graalvm.compiler.serviceprovider.GraalServices;
import org.junit.Test;

public class SingleImplementorInterfaceTest extends GraalCompilerTest {

    public interface Interface0 {
        void interfaceMethod();
    }

    public interface Interface1 extends Interface0 {

    }

    public interface Interface2 extends Interface1 {
    }

    @SuppressWarnings("all")
    public static class SingleImplementor1 implements Interface1 {
        public void interfaceMethod() {
        }
    }

    // Requires that the CHA analysis starts from the referenced type. Since {@code
    // SingleImplementor1}
    // is not a single implementor of {@code Interface2} devirtualization shouldn't happen.
    @SuppressWarnings("all")
    private static void singleImplementorInterfaceSnippet1(Interface2 i) {
        i.interfaceMethod();
    }

    // Devirtualization should happen in this case.
    @SuppressWarnings("all")
    private static void singleImplementorInterfaceSnippet2(Interface1 i) {
        i.interfaceMethod();
    }

    @Test
    public void testSingleImplementorInterfaceDevirtualization1() {
        ResolvedJavaType singleImplementorType = getMetaAccess().lookupJavaType(SingleImplementor1.class);
        ResolvedJavaType expectedReferencedType = getMetaAccess().lookupJavaType(Interface2.class);
        singleImplementorType.initialize();
        StructuredGraph graph = parseEager("singleImplementorInterfaceSnippet1", StructuredGraph.AllowAssumptions.YES);
        createCanonicalizerPhase().apply(graph, getProviders());
        // Devirtualization shouldn't work in this case. The invoke should remain intact.
        InvokeNode invoke = graph.getNodes().filter(InvokeNode.class).first();
        assertTrue(invoke != null, "Should have an invoke");
        assertTrue(invoke.callTarget().invokeKind() == CallTargetNode.InvokeKind.Interface, "Should still be an interface call");
        if (GraalServices.hasLookupReferencedType()) {
            assertTrue(invoke.callTarget().referencedType() != null, "Invoke should have a reference class set");
            assertTrue(invoke.callTarget().referencedType().equals(expectedReferencedType));
        }
    }

    @Test
    public void testSingleImplementorInterfaceDevirtualization2() {
        ResolvedJavaType singleImplementorType = getMetaAccess().lookupJavaType(SingleImplementor1.class);
        singleImplementorType.initialize();
        StructuredGraph graph = parseEager("singleImplementorInterfaceSnippet2", StructuredGraph.AllowAssumptions.YES);
        createCanonicalizerPhase().apply(graph, getProviders());
        InvokeNode invoke = graph.getNodes().filter(InvokeNode.class).first();
        assertTrue(invoke != null, "Should have an invoke");
        if (GraalServices.hasLookupReferencedType()) {
            assertTrue(invoke.callTarget().invokeKind() == CallTargetNode.InvokeKind.Special, "Should be devirtualized");
            InstanceOfNode instanceOfNode = graph.getNodes().filter(InstanceOfNode.class).first();
            assertTrue(instanceOfNode != null, "Missing the subtype check");
            assertTrue(instanceOfNode.getCheckedStamp().type().equals(singleImplementorType), "Checking against a wrong type");
        } else {
            assertTrue(invoke.callTarget().invokeKind() == CallTargetNode.InvokeKind.Interface, "Should not be devirtualized");
        }
    }

    @Test
    public void testSingleImplementorInterfaceInlining1() {
        ResolvedJavaType singleImplementorType = getMetaAccess().lookupJavaType(SingleImplementor1.class);
        ResolvedJavaType expectedReferencedType = getMetaAccess().lookupJavaType(Interface2.class);
        singleImplementorType.initialize();
        StructuredGraph graph = parseEager("singleImplementorInterfaceSnippet1", StructuredGraph.AllowAssumptions.YES);
        HighTierContext context = new HighTierContext(getProviders(), getDefaultGraphBuilderSuite(), OptimisticOptimizations.ALL);
        createInliningPhase().apply(graph, context);
        // Inlining shouldn't do anything
        InvokeNode invoke = graph.getNodes().filter(InvokeNode.class).first();
        assertTrue(invoke != null, "Should have an invoke");
        if (GraalServices.hasLookupReferencedType()) {
            assertTrue(invoke.callTarget().referencedType() != null, "Invoke should have a reference class set");
            assertTrue(invoke.callTarget().invokeKind() == CallTargetNode.InvokeKind.Interface, "Should still be an interface call");
            assertTrue(invoke.callTarget().referencedType().equals(expectedReferencedType));
        } else {
            assertTrue(invoke.callTarget().invokeKind() == CallTargetNode.InvokeKind.Interface, "Should not be devirtualized");
        }
    }

    @Test
    public void testSingleImplementorInterfaceInlining2() {
        ResolvedJavaType singleImplementorType = getMetaAccess().lookupJavaType(SingleImplementor1.class);
        ResolvedJavaType expectedReferencedType = getMetaAccess().lookupJavaType(Interface1.class);
        singleImplementorType.initialize();
        StructuredGraph graph = parseEager("singleImplementorInterfaceSnippet2", StructuredGraph.AllowAssumptions.YES);
        HighTierContext context = new HighTierContext(getProviders(), getDefaultGraphBuilderSuite(), OptimisticOptimizations.ALL);
        createInliningPhase().apply(graph, context);

        // Right now inlining will not do anything, but if it starts doing devirtualization of
        // interface calls
        // in the future there should be a subtype check.
        InvokeNode invoke = graph.getNodes().filter(InvokeNode.class).first();
        if (invoke != null) {
            assertTrue(invoke.callTarget().invokeKind() == CallTargetNode.InvokeKind.Interface, "Should still be an interface call");
            if (GraalServices.hasLookupReferencedType()) {
                assertTrue(invoke.callTarget().referencedType() != null, "Invoke should have a reference class set");
                assertTrue(invoke.callTarget().referencedType().equals(expectedReferencedType));
            }
        } else {
            InstanceOfNode instanceOfNode = graph.getNodes().filter(InstanceOfNode.class).first();
            assertTrue(instanceOfNode != null, "Missing the subtype check");
            assertTrue(instanceOfNode.getCheckedStamp().type().equals(singleImplementorType), "Checking against a wrong type");
        }
    }
}

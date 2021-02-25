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


package org.graalvm.compiler.core.test;

import jdk.vm.ci.meta.ResolvedJavaMethod;
import org.graalvm.compiler.nodes.FieldLocationIdentity;
import org.graalvm.compiler.nodes.IfNode;
import org.graalvm.compiler.nodes.LogicNode;
import org.graalvm.compiler.nodes.ReturnNode;
import org.graalvm.compiler.nodes.calc.ObjectEqualsNode;
import org.graalvm.compiler.nodes.memory.ReadNode;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class HashMapGetTest extends SubprocessTest {

    public static <K, V> void mapGet(HashMap<K, V> map, K key) {
        map.get(key);
    }

    public void hashMapTest() {
        HashMap<Integer, Integer> map = new HashMap<>();
        ResolvedJavaMethod get = getResolvedJavaMethod(HashMapGetTest.class, "mapGet");
        for (int i = 0; i < 10000; i++) {
            mapGet(map, i);
            map.put(i, i);
            mapGet(map, i);
        }
        test(get, null, map, 0);
        for (IfNode ifNode : lastCompiledGraph.getNodes(IfNode.TYPE)) {
            LogicNode condition = ifNode.condition();
            if (ifNode.getTrueSuccessorProbability() < 0.4 && ifNode.predecessor() instanceof ReadNode && condition instanceof ObjectEqualsNode) {
                ReadNode read = (ReadNode) ifNode.predecessor();
                if (read.getLocationIdentity() instanceof FieldLocationIdentity && ((FieldLocationIdentity) read.getLocationIdentity()).getField().getName().contains("key")) {
                    assertTrue(ifNode.trueSuccessor().next() instanceof ReturnNode, "Expected return after %s, got %s", ifNode.trueSuccessor(), ifNode.trueSuccessor().next());
                }
            }
        }
    }

    @Test
    public void hashMapTestInSubprocess() throws IOException, InterruptedException {
        launchSubprocess(this::hashMapTest);
    }

}

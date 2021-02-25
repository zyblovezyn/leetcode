/*
 * Copyright (c) 2013, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.hotspot.replacements;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.replacements.nodes.ReflectionGetCallerClassNode;

import jdk.vm.ci.hotspot.HotSpotResolvedJavaMethod;
import jdk.vm.ci.meta.ResolvedJavaMethod;

@NodeInfo
public final class HotSpotReflectionGetCallerClassNode extends ReflectionGetCallerClassNode {

    public static final NodeClass<HotSpotReflectionGetCallerClassNode> TYPE = NodeClass.create(HotSpotReflectionGetCallerClassNode.class);

    public HotSpotReflectionGetCallerClassNode(MacroParams p) {
        super(TYPE, p);
    }

    @Override
    protected boolean isCallerSensitive(ResolvedJavaMethod method) {
        return ((HotSpotResolvedJavaMethod) method).isCallerSensitive();
    }

    @Override
    protected boolean ignoredBySecurityStackWalk(ResolvedJavaMethod method) {
        return ((HotSpotResolvedJavaMethod) method).ignoredBySecurityStackWalk();
    }
}

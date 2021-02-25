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


package org.graalvm.compiler.nodes.extended;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.iterators.NodeIterable;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.StartNode;
import org.graalvm.compiler.nodes.spi.Lowerable;

@NodeInfo
public final class OSRStartNode extends StartNode implements Lowerable {
    public static final NodeClass<OSRStartNode> TYPE = NodeClass.create(OSRStartNode.class);

    public OSRStartNode() {
        super(TYPE);
    }

    public NodeIterable<OSRLocalNode> getOSRLocals() {
        return usages().filter(OSRLocalNode.class);
    }
}

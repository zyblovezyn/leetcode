/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.phases.common.util;

import java.util.EnumSet;
import java.util.Set;

import jdk.internal.vm.compiler.collections.EconomicSet;
import jdk.internal.vm.compiler.collections.Equivalence;
import org.graalvm.compiler.graph.Graph.NodeEvent;
import org.graalvm.compiler.graph.Graph.NodeEventListener;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.graph.Node.IndirectCanonicalization;
import org.graalvm.compiler.nodes.AbstractBeginNode;

/**
 * A simple {@link NodeEventListener} implementation that accumulates event nodes in a
 * {@link EconomicSet}.
 */
public class EconomicSetNodeEventListener extends NodeEventListener {

    private final EconomicSet<Node> nodes;
    private final Set<NodeEvent> filter;

    /**
     * Creates a {@link NodeEventListener} that collects nodes from all events.
     */
    public EconomicSetNodeEventListener() {
        this.nodes = EconomicSet.create(Equivalence.IDENTITY);
        this.filter = EnumSet.of(NodeEvent.INPUT_CHANGED, NodeEvent.NODE_ADDED, NodeEvent.ZERO_USAGES);
    }

    /**
     * Creates a {@link NodeEventListener} that collects nodes from all events that match a given
     * filter.
     */
    public EconomicSetNodeEventListener(Set<NodeEvent> filter) {
        this.nodes = EconomicSet.create(Equivalence.IDENTITY);
        this.filter = filter;
    }

    /**
     * Excludes a given event from those for which nodes are collected.
     */
    public EconomicSetNodeEventListener exclude(NodeEvent e) {
        filter.remove(e);
        return this;
    }

    @Override
    public void changed(NodeEvent e, Node node) {
        if (filter.contains(e)) {
            add(node);
            if (node instanceof IndirectCanonicalization) {
                for (Node usage : node.usages()) {
                    add(usage);
                }
            }

            if (node instanceof AbstractBeginNode) {
                AbstractBeginNode abstractBeginNode = (AbstractBeginNode) node;
                add(abstractBeginNode.predecessor());
            }
        }
    }

    private void add(Node n) {
        if (n != null) {
            nodes.add(n);
        }
    }

    /**
     * Gets the set being used to accumulate the nodes communicated to this listener.
     */
    public EconomicSet<Node> getNodes() {
        return nodes;
    }
}

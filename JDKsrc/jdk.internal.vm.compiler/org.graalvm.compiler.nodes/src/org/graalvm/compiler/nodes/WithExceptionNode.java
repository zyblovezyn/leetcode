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
 */


package org.graalvm.compiler.nodes;

import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.extended.ForeignCallWithExceptionNode;
import org.graalvm.compiler.nodes.memory.MultiMemoryKill;
import org.graalvm.compiler.nodes.memory.SingleMemoryKill;
import org.graalvm.compiler.nodes.util.GraphUtil;

/**
 * Base class for fixed nodes that have exactly two successors: A "next" successor for normal
 * execution, and an "exception edge" successor for exceptional control flow.
 */
@NodeInfo
public abstract class WithExceptionNode extends ControlSplitNode {

    public static final NodeClass<WithExceptionNode> TYPE = NodeClass.create(WithExceptionNode.class);

    protected WithExceptionNode(NodeClass<? extends WithExceptionNode> c, Stamp stamp) {
        super(c, stamp);
    }

    private static final double EXCEPTION_PROBABILITY = 1e-5;

    @Successor protected AbstractBeginNode next;
    @Successor protected AbstractBeginNode exceptionEdge;

    public AbstractBeginNode next() {
        return next;
    }

    public void setNext(AbstractBeginNode x) {
        updatePredecessor(next, x);
        next = x;
    }

    @Override
    public AbstractBeginNode getPrimarySuccessor() {
        return this.next();
    }

    public AbstractBeginNode exceptionEdge() {
        return exceptionEdge;
    }

    public void setExceptionEdge(AbstractBeginNode x) {
        updatePredecessor(exceptionEdge, x);
        exceptionEdge = x;
    }

    public void killExceptionEdge() {
        AbstractBeginNode edge = exceptionEdge();
        setExceptionEdge(null);
        GraphUtil.killCFG(edge);
    }

    @Override
    public double probability(AbstractBeginNode successor) {
        return successor == next ? 1 - EXCEPTION_PROBABILITY : EXCEPTION_PROBABILITY;
    }

    @Override
    public boolean setProbability(AbstractBeginNode successor, double value) {
        // Cannot set probability for nodes with exceptions.
        return false;
    }

    @Override
    public int getSuccessorCount() {
        return 2;
    }

    /**
     * Converts this node into a variant with the same semantics but without an exception edge. The
     * default implementation does not change the node class, but instead only marks the exception
     * path as unused. A later lowering of the node needs to take care of the actual removal of the
     * exception path, e.g., by lowering into a {@link ForeignCallWithExceptionNode}.
     */
    public FixedNode replaceWithNonThrowing() {
        killExceptionEdge();
        AbstractBeginNode newExceptionEdge = graph().add(new UnreachableBeginNode());
        newExceptionEdge.setNext(graph().add(new UnreachableControlSinkNode()));
        setExceptionEdge(newExceptionEdge);
        return this;
    }

    /**
     * Create a begin node appropriate as this node's next successor. In particular, if this node is
     * a memory kill, this should create a {@link KillingBeginNode} or {@link MultiKillingBeginNode}
     * with the appropriate location identities.
     */
    public AbstractBeginNode createNextBegin() {
        if (this instanceof SingleMemoryKill) {
            return KillingBeginNode.create(((SingleMemoryKill) this).getKilledLocationIdentity());
        } else if (this instanceof MultiMemoryKill) {
            return MultiKillingBeginNode.create(((MultiMemoryKill) this).getKilledLocationIdentities());
        } else {
            return new BeginNode();
        }
    }
}

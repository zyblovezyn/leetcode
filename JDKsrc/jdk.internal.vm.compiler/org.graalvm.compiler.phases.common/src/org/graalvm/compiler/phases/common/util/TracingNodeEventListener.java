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


package org.graalvm.compiler.phases.common.util;

import org.graalvm.compiler.debug.TTY;
import org.graalvm.compiler.graph.Graph.NodeEvent;
import org.graalvm.compiler.graph.Graph.NodeEventListener;
import org.graalvm.compiler.graph.Node;

/**
 * A simple {@link NodeEventListener} implementation that traces events to TTY for debugging
 * purposes.
 */
public class TracingNodeEventListener extends NodeEventListener {

    @Override
    public void changed(NodeEvent e, Node node) {
        TTY.println(e.toString() + ": " + node);
    }
}

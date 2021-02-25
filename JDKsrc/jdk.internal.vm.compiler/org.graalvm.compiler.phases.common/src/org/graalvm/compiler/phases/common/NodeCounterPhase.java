/*
 * Copyright (c) 2018, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.phases.common;

import org.graalvm.compiler.debug.DebugContext;
import org.graalvm.compiler.graph.Node;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.spi.CoreProviders;
import org.graalvm.compiler.options.Option;
import org.graalvm.compiler.options.OptionKey;
import org.graalvm.compiler.options.OptionType;
import org.graalvm.compiler.phases.BasePhase;

public class NodeCounterPhase extends BasePhase<CoreProviders> {

    private Stage stage;

    public NodeCounterPhase(Stage stage) {
        this.stage = stage;
    }

    public enum Stage {
        INIT,
        EARLY,
        LATE
    }

    public static class Options {
        // @formatter:off
        @Option(help = "Counts the number of instances of each node class.", type = OptionType.Debug)
        public static final OptionKey<Boolean> NodeCounters = new OptionKey<>(false);
        // @formatter:on
    }

    @Override
    protected void run(StructuredGraph graph, CoreProviders context) {

        for (Node node : graph.getNodes()) {
            String nodeName = node.getNodeClass().getClazz().getSimpleName();

            DebugContext.counter("NodeCounter_%s_%s", this.stage, nodeName).increment(node.getDebug());
        }
    }
}

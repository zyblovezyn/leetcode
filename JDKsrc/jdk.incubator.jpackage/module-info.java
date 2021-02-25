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
 *
 *
 */

/**
 * Defines the Java Packaging tool, jpackage.
 *
 * <p>jpackage is a tool for generating self-contained application bundles.
 *
 * <p> This module provides the equivalent of command-line access to <em>jpackage</em>
 * via the {@link java.util.spi.ToolProvider ToolProvider} SPI.
 * Instances of the tool can be obtained by calling
 * {@link java.util.spi.ToolProvider#findFirst ToolProvider.findFirst}
 * or the {@link java.util.ServiceLoader service loader} with the name
 * {@code "jpackage"}.
 *
 * @implNote The {@code jpackage} tool is not thread-safe. An application
 * should not call either of the
 * {@link java.util.spi.ToolProvider ToolProvider} {@code run} methods
 * concurrently, even with separate {@code "jpackage"} {@code ToolProvider}
 * instances, or undefined behavior may result.
 *
 *
 * @moduleGraph
 * @since 14
 */

module jdk.incubator.jpackage {
    requires jdk.jlink;
    requires java.desktop;

    uses jdk.incubator.jpackage.internal.Bundler;
    uses jdk.incubator.jpackage.internal.Bundlers;
    provides java.util.spi.ToolProvider with jdk.incubator.jpackage.internal.JPackageToolProvider;
    provides jdk.incubator.jpackage.internal.Bundler with
        jdk.incubator.jpackage.internal.WinAppBundler,
        jdk.incubator.jpackage.internal.WinExeBundler,
        jdk.incubator.jpackage.internal.WinMsiBundler;
    provides jdk.incubator.jpackage.internal.Bundlers with jdk.incubator.jpackage.internal.BasicBundlers;
}

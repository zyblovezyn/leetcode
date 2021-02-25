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


package org.graalvm.compiler.options;

import java.util.ServiceLoader;

public class ModuleSupport {

    static Iterable<OptionDescriptors> getOptionsLoader() {
        /*
         * The Graal module (i.e., jdk.internal.vm.compiler) is loaded by the platform class loader
         * as of JDK 9. Modules that depend on and extend Graal are loaded by the app class loader.
         * As such, we need to start the provider search at the app class loader instead of the
         * platform class loader.
         */
        return ServiceLoader.load(OptionDescriptors.class, ClassLoader.getSystemClassLoader());
    }
}

/*
 * Copyright (c) 2016, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.api.test;

/**
 * A class loader that exports all packages in the module defining the class loader to all classes
 * in the unnamed module associated with the loader.
 */
public class ExportingClassLoader extends ClassLoader {
    public ExportingClassLoader() {
        ModuleSupport.exportAllPackagesTo(getClass(), this);
    }

    public ExportingClassLoader(ClassLoader parent) {
        super(parent);
        ModuleSupport.exportAllPackagesTo(getClass(), this);
    }
}

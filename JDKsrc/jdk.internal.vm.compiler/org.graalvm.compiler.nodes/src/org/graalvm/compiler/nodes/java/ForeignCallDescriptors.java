/*
 * Copyright (c) 2014, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.java;

import org.graalvm.compiler.core.common.spi.ForeignCallSignature;

/**
 * The foreign call descriptors used by nodes in this package.
 * <p>
 * Using a separate class for such descriptors prevents an access from triggering unwanted class
 * initialization during runtime initialization.
 */
public class ForeignCallDescriptors {

    /**
     * @see RegisterFinalizerNode
     */
    public static final ForeignCallSignature REGISTER_FINALIZER = new ForeignCallSignature("registerFinalizer", void.class, Object.class);

}

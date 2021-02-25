/*
 * Copyright (c) 2014, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.spi;

import org.graalvm.compiler.core.common.type.AbstractPointerStamp;
import org.graalvm.compiler.core.common.type.ObjectStamp;
import org.graalvm.compiler.nodes.extended.LoadHubNode;

/**
 * Provides a capability for creating platform dependent stamps.
 */
public interface StampProvider {

    /**
     * Create the stamp of the {@link LoadHubNode hub} of an object.
     */
    AbstractPointerStamp createHubStamp(ObjectStamp object);

    /**
     * Create the stamp of a pointer to a method.
     */
    AbstractPointerStamp createMethodStamp();
}

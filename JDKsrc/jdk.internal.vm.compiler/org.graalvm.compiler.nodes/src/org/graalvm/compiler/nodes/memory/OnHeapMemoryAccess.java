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


package org.graalvm.compiler.nodes.memory;

/**
 *
 * A special form of {@linkplain MemoryAccess} requiring barrier information for garbage collection.
 */
public interface OnHeapMemoryAccess extends MemoryAccess {

    /**
     * The types of (write/read) barriers attached to stores.
     */
    enum BarrierType {
        /**
         * Primitive access which do not necessitate barriers.
         */
        NONE,
        /**
         * Array object access.
         */
        ARRAY,
        /**
         * Field object access.
         */
        FIELD,
        /**
         * Unknown (aka field or array) object access.
         */
        UNKNOWN,
        /**
         * Weak field access (e.g. Hotspot's Reference.referent field).
         */
        WEAK_FIELD,
        /**
         * An access which requires a dynamic check for Weak field access (e.g. Hotspot's
         * Reference.referent field).
         */
        MAYBE_WEAK_FIELD
    }

    /**
     * Gets the write barrier type for that particular access.
     */
    BarrierType getBarrierType();

}

/*
 *  Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 *  ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
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
 *
 */

package jdk.internal.access.foreign;

/**
 * This proxy interface is required to allow instances of the {@code MemorySegment} interface (which is defined inside
 * an incubating module) to be accessed from the memory access var handles.
 */
public interface MemorySegmentProxy {
    void checkValidState();
}

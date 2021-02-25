/*
 * Copyright (c) 2017, 2020, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.spi.LIRLowerable;

public interface LIRLowerableAccess extends LIRLowerable, AddressableMemoryAccess {
    Stamp getAccessStamp(NodeView view);
}

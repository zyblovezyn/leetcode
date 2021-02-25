/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2019, Red Hat Inc. All rights reserved.
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


package org.graalvm.compiler.nodes.gc;

import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.extended.RawLoadNode;
import org.graalvm.compiler.nodes.extended.RawStoreNode;
import org.graalvm.compiler.nodes.memory.FixedAccessNode;
import org.graalvm.compiler.nodes.memory.OnHeapMemoryAccess.BarrierType;

import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.ResolvedJavaField;

public interface BarrierSet {
    void addBarriers(FixedAccessNode n);

    BarrierType fieldLoadBarrierType(ResolvedJavaField field, JavaKind storageKind);

    BarrierType fieldStoreBarrierType(ResolvedJavaField field, JavaKind storageKind);

    BarrierType readBarrierType(RawLoadNode load);

    BarrierType storeBarrierType(RawStoreNode store);

    BarrierType arrayStoreBarrierType(JavaKind storageKind);

    BarrierType guessStoreBarrierType(ValueNode object, ValueNode value);
}

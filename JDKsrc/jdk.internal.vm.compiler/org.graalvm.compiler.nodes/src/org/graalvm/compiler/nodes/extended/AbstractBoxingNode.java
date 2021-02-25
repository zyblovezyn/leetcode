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


package org.graalvm.compiler.nodes.extended;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_2;

import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.debug.GraalError;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.Canonicalizable;
import org.graalvm.compiler.nodeinfo.InputType;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.FixedWithNextNode;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.ValueNodeUtil;
import org.graalvm.compiler.nodes.memory.MemoryAccess;
import org.graalvm.compiler.nodes.memory.MemoryKill;
import org.graalvm.compiler.nodes.spi.Lowerable;
import org.graalvm.compiler.nodes.spi.Virtualizable;
import jdk.internal.vm.compiler.word.LocationIdentity;

import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.ResolvedJavaField;
import jdk.vm.ci.meta.ResolvedJavaType;

@NodeInfo(cycles = CYCLES_2, size = SIZE_2)
public abstract class AbstractBoxingNode extends FixedWithNextNode implements Virtualizable, Lowerable, Canonicalizable.Unary<ValueNode>, MemoryAccess {
    public static final NodeClass<AbstractBoxingNode> TYPE = NodeClass.create(AbstractBoxingNode.class);

    @Input protected ValueNode value;
    protected final JavaKind boxingKind;
    protected final LocationIdentity accessedLocation;

    public AbstractBoxingNode(NodeClass<? extends AbstractBoxingNode> c, ValueNode value, JavaKind boxingKind, Stamp s, LocationIdentity accessedLocation) {
        super(c, s);
        this.value = value;
        this.boxingKind = boxingKind;
        this.accessedLocation = accessedLocation;
    }

    public static ResolvedJavaField getValueField(ResolvedJavaType resultType) {
        for (ResolvedJavaField f : resultType.getInstanceFields(false)) {
            if (f.getName().equals("value")) {
                return f;
            }
        }
        throw GraalError.shouldNotReachHere();
    }

    @Override
    public ValueNode getValue() {
        return value;
    }

    public JavaKind getBoxingKind() {
        return boxingKind;
    }

    @Override
    public MemoryKill getLastLocationAccess() {
        return lastLocationAccess;
    }

    @OptionalInput(InputType.Memory) MemoryKill lastLocationAccess;

    @Override
    public void setLastLocationAccess(MemoryKill newlla) {
        updateUsages(ValueNodeUtil.asNode(lastLocationAccess), ValueNodeUtil.asNode(newlla));
        lastLocationAccess = newlla;
    }

    @Override
    public LocationIdentity getLocationIdentity() {
        return accessedLocation;
    }
}

/*
 * Copyright (c) 2009, 2020, Oracle and/or its affiliates. All rights reserved.
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

import java.lang.ref.Reference;
import java.util.Collections;

import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.core.common.type.TypeReference;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.FrameState;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.spi.VirtualizableAllocation;
import org.graalvm.compiler.nodes.spi.VirtualizerTool;
import org.graalvm.compiler.nodes.virtual.VirtualInstanceNode;

import jdk.vm.ci.meta.ResolvedJavaField;
import jdk.vm.ci.meta.ResolvedJavaType;

/**
 * The {@code NewInstanceNode} represents the allocation of an instance class object.
 */
@NodeInfo(nameTemplate = "New {p#instanceClass/s}")
public class NewInstanceNode extends AbstractNewObjectNode implements VirtualizableAllocation {

    public static final NodeClass<NewInstanceNode> TYPE = NodeClass.create(NewInstanceNode.class);
    protected final ResolvedJavaType instanceClass;

    public NewInstanceNode(ResolvedJavaType type, boolean fillContents) {
        this(TYPE, type, fillContents, null);
    }

    public NewInstanceNode(ResolvedJavaType type, boolean fillContents, FrameState stateBefore) {
        this(TYPE, type, fillContents, stateBefore);
    }

    protected NewInstanceNode(NodeClass<? extends NewInstanceNode> c, ResolvedJavaType type, boolean fillContents, FrameState stateBefore) {
        super(c, StampFactory.objectNonNull(TypeReference.createExactTrusted(type)), fillContents, stateBefore);
        assert !type.isArray() && !type.isInterface() && !type.isPrimitive() && !type.isAbstract() : type;
        this.instanceClass = type;
    }

    /**
     * Gets the instance class being allocated by this node.
     *
     * @return the instance class allocated
     */
    public ResolvedJavaType instanceClass() {
        return instanceClass;
    }

    @Override
    public void virtualize(VirtualizerTool tool) {
        /*
         * Reference objects can escape into their ReferenceQueue at any safepoint, therefore
         * they're excluded from escape analysis.
         */
        if (!tool.getMetaAccess().lookupJavaType(Reference.class).isAssignableFrom(instanceClass)) {
            VirtualInstanceNode virtualObject = new VirtualInstanceNode(instanceClass(), true);
            ResolvedJavaField[] fields = virtualObject.getFields();
            ValueNode[] state = new ValueNode[fields.length];
            for (int i = 0; i < state.length; i++) {
                state[i] = ConstantNode.defaultForKind(tool.getMetaAccessExtensionProvider().getStorageKind(fields[i].getType()), graph());
            }
            tool.createVirtualObject(virtualObject, state, Collections.<MonitorIdNode> emptyList(), false);
            tool.replaceWithVirtual(virtualObject);
        }
    }
}

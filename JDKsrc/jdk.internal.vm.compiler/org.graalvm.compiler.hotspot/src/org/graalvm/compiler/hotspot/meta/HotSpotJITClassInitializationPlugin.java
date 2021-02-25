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


package org.graalvm.compiler.hotspot.meta;

import static org.graalvm.compiler.bytecode.Bytecodes.GETSTATIC;
import static org.graalvm.compiler.bytecode.Bytecodes.INVOKESTATIC;
import static org.graalvm.compiler.bytecode.Bytecodes.NEW;
import static org.graalvm.compiler.bytecode.Bytecodes.PUTSTATIC;

import java.util.function.Supplier;

import org.graalvm.compiler.hotspot.nodes.KlassBeingInitializedCheckNode;
import org.graalvm.compiler.hotspot.nodes.type.KlassPointerStamp;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.FrameState;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.graphbuilderconf.ClassInitializationPlugin;
import org.graalvm.compiler.nodes.graphbuilderconf.GraphBuilderContext;

import jdk.vm.ci.hotspot.HotSpotResolvedObjectType;
import jdk.vm.ci.meta.ConstantPool;
import jdk.vm.ci.meta.ResolvedJavaType;

public final class HotSpotJITClassInitializationPlugin implements ClassInitializationPlugin {
    @Override
    public boolean apply(GraphBuilderContext builder, ResolvedJavaType type, Supplier<FrameState> frameState, ValueNode[] classInit) {
        if (!type.isInitialized() && (type.isInstanceClass() || type.isInterface())) {
            int code = builder.getCode().getCode()[builder.bci()] & 0xff;
            switch (code) {
                case INVOKESTATIC:
                case GETSTATIC:
                case PUTSTATIC:
                case NEW:
                    ValueNode typeConstant = ConstantNode.forConstant(KlassPointerStamp.klass(), ((HotSpotResolvedObjectType) type).klass(), builder.getMetaAccess());
                    builder.add(new KlassBeingInitializedCheckNode(typeConstant));
                    return true;
                default:

            }
        }
        return false;
    }

    @Override
    public boolean supportsLazyInitialization(ConstantPool cp) {
        return false;
    }

    @Override
    public void loadReferencedType(GraphBuilderContext builder, ConstantPool cp, int cpi, int opcode) {
        cp.loadReferencedType(cpi, opcode);
    }

}

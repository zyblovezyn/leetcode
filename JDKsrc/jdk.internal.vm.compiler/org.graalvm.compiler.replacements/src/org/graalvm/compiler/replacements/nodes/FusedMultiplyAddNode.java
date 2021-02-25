/*
 * Copyright (c) 2019, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.nodes;

import static org.graalvm.compiler.nodeinfo.NodeCycles.CYCLES_2;
import static org.graalvm.compiler.nodeinfo.NodeSize.SIZE_1;

import org.graalvm.compiler.core.common.type.FloatStamp;
import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.CanonicalizerTool;
import org.graalvm.compiler.lir.gen.ArithmeticLIRGeneratorTool;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.ConstantNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.calc.TernaryNode;
import org.graalvm.compiler.nodes.spi.ArithmeticLIRLowerable;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;

import jdk.vm.ci.meta.JavaConstant;
import jdk.vm.ci.meta.JavaKind;
import org.graalvm.compiler.serviceprovider.GraalServices;

@NodeInfo(cycles = CYCLES_2, size = SIZE_1)
public final class FusedMultiplyAddNode extends TernaryNode implements ArithmeticLIRLowerable {

    public static final NodeClass<FusedMultiplyAddNode> TYPE = NodeClass.create(FusedMultiplyAddNode.class);

    public FusedMultiplyAddNode(ValueNode x, ValueNode y, ValueNode z) {
        super(TYPE, computeStamp(x.stamp(NodeView.DEFAULT), y.stamp(NodeView.DEFAULT), z.stamp(NodeView.DEFAULT)), x, y, z);
        assert x.getStackKind().isNumericFloat();
        assert y.getStackKind().isNumericFloat();
        assert z.getStackKind().isNumericFloat();
    }

    @Override
    public Stamp foldStamp(Stamp stampX, Stamp stampY, Stamp stampZ) {
        return computeStamp(stampX, stampY, stampZ);
    }

    private static Stamp computeStamp(Stamp stampX, Stamp stampY, Stamp stampZ) {
        if (stampX.isEmpty()) {
            return stampX;
        }
        if (stampY.isEmpty()) {
            return stampY;
        }
        if (stampZ.isEmpty()) {
            return stampZ;
        }
        JavaConstant constantX = ((FloatStamp) stampX).asConstant();
        JavaConstant constantY = ((FloatStamp) stampY).asConstant();
        JavaConstant constantZ = ((FloatStamp) stampZ).asConstant();
        if (constantX != null && constantY != null && constantZ != null) {
            if (stampX.getStackKind() == JavaKind.Float) {
                float result = GraalServices.fma(constantX.asFloat(), constantY.asFloat(), constantZ.asFloat());
                if (Float.isNaN(result)) {
                    return StampFactory.forFloat(JavaKind.Float, Double.NaN, Double.NaN, false);
                } else {
                    return StampFactory.forFloat(JavaKind.Float, result, result, true);
                }
            } else {
                double result = GraalServices.fma(constantX.asDouble(), constantY.asDouble(), constantZ.asDouble());
                assert stampX.getStackKind() == JavaKind.Double;
                if (Double.isNaN(result)) {
                    return StampFactory.forFloat(JavaKind.Double, Double.NaN, Double.NaN, false);
                } else {
                    return StampFactory.forFloat(JavaKind.Double, result, result, true);
                }
            }
        }

        return stampX.unrestricted();
    }

    @Override
    public ValueNode canonical(CanonicalizerTool tool, ValueNode forX, ValueNode forY, ValueNode forZ) {
        if (forX.isConstant() && forY.isConstant() && forZ.isConstant()) {
            JavaConstant constantX = forX.asJavaConstant();
            JavaConstant constantY = forY.asJavaConstant();
            JavaConstant constantZ = forZ.asJavaConstant();
            if (forX.getStackKind() == JavaKind.Float) {
                return ConstantNode.forFloat(GraalServices.fma(constantX.asFloat(), constantY.asFloat(), constantZ.asFloat()));
            } else {
                assert forX.getStackKind() == JavaKind.Double;
                return ConstantNode.forDouble(GraalServices.fma(constantX.asDouble(), constantY.asDouble(), constantZ.asDouble()));
            }
        }
        return this;
    }

    @Override
    public void generate(NodeLIRBuilderTool builder, ArithmeticLIRGeneratorTool gen) {
        builder.setResult(this, gen.emitFusedMultiplyAdd(builder.operand(getX()), builder.operand(getY()), builder.operand(getZ())));
    }
}

/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.nodes.util;

import org.graalvm.compiler.core.common.NumUtil;
import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.graalvm.compiler.core.common.type.StampFactory;
import org.graalvm.compiler.nodes.LogicNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;
import org.graalvm.compiler.nodes.calc.IntegerLessThanNode;

import jdk.vm.ci.code.CodeUtil;

public class SignedIntegerHelper extends IntegerHelper {
    public SignedIntegerHelper(int bits) {
        super(bits);
    }

    @Override
    public long upperBound(IntegerStamp stamp) {
        assert stamp.getBits() == bits;
        return stamp.upperBound();
    }

    @Override
    public long lowerBound(IntegerStamp stamp) {
        assert stamp.getBits() == bits;
        return stamp.lowerBound();
    }

    @Override
    protected int rawCompare(long a, long b) {
        return Long.compare(a, b);
    }

    @Override
    protected long rawMin(long a, long b) {
        return Math.min(a, b);
    }

    @Override
    protected long rawMax(long a, long b) {
        return Math.max(a, b);
    }

    @Override
    public long cast(long a) {
        return CodeUtil.signExtend(a, bits);
    }

    @Override
    public long minValue() {
        return NumUtil.minValue(bits);
    }

    @Override
    public long maxValue() {
        return NumUtil.maxValue(bits);
    }

    @Override
    public IntegerStamp stamp(long min, long max) {
        return StampFactory.forInteger(bits, cast(min), cast(max));
    }

    @Override
    public LogicNode createCompareNode(ValueNode x, ValueNode y, NodeView view) {
        return IntegerLessThanNode.create(x, y, view);
    }
}

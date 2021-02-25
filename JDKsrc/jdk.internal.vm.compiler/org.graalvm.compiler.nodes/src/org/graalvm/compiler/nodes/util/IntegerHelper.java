/*
 * Copyright (c) 2018, 2019, Oracle and/or its affiliates. All rights reserved.
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

import org.graalvm.compiler.core.common.type.IntegerStamp;
import org.graalvm.compiler.nodes.LogicNode;
import org.graalvm.compiler.nodes.NodeView;
import org.graalvm.compiler.nodes.ValueNode;

public abstract class IntegerHelper {
    protected final int bits;

    protected IntegerHelper(int bits) {
        this.bits = bits;
    }

    public abstract long upperBound(IntegerStamp stamp);

    public abstract long lowerBound(IntegerStamp stamp);

    public int compare(long a, long b) {
        return rawCompare(cast(a), cast(b));
    }

    public boolean isGreater(long a, long b) {
        return compare(a, b) > 0;
    }

    public boolean isSmaller(long a, long b) {
        return compare(a, b) < 0;
    }

    public boolean isGreaterEqual(long a, long b) {
        return compare(a, b) >= 0;
    }

    public boolean isSmallerEqual(long a, long b) {
        return compare(a, b) <= 0;
    }

    public long min(long a, long b) {
        return rawMin(cast(a), cast(b));
    }

    public long max(long a, long b) {
        return rawMax(cast(a), cast(b));
    }

    public abstract long cast(long a);

    public abstract long minValue();

    public abstract long maxValue();

    public abstract IntegerStamp stamp(long min, long max);

    public abstract LogicNode createCompareNode(ValueNode x, ValueNode y, NodeView view);

    protected abstract int rawCompare(long a, long b);

    protected abstract long rawMin(long a, long b);

    protected abstract long rawMax(long a, long b);
}

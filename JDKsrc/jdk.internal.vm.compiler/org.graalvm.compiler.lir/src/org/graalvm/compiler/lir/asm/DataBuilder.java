/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.lir.asm;

import org.graalvm.compiler.code.DataSection.Data;

import jdk.vm.ci.meta.Constant;

public abstract class DataBuilder {
    public abstract Data createDataItem(Constant c);
}

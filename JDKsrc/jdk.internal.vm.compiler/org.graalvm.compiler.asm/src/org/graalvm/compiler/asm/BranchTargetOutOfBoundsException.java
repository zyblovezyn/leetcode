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


package org.graalvm.compiler.asm;

import org.graalvm.compiler.core.common.GraalBailoutException;

@SuppressWarnings("serial")
public class BranchTargetOutOfBoundsException extends GraalBailoutException {

    public BranchTargetOutOfBoundsException(boolean permanent, String format, Object... args) {
        super(permanent, format, args);
    }

    public BranchTargetOutOfBoundsException(String format, Object... args) {
        super(format, args);
    }

    public BranchTargetOutOfBoundsException(Throwable cause, String format, Object... args) {
        super(cause, format, args);
    }

}

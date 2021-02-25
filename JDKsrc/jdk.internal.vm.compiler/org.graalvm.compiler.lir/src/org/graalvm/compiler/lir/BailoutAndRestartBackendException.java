/*
 * Copyright (c) 2016, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.lir;

import org.graalvm.compiler.core.common.PermanentBailoutException;

/**
 * Restarts the {@link LIR low-level} compilation with a modified configuration.
 */
public abstract class BailoutAndRestartBackendException extends PermanentBailoutException {

    private static final long serialVersionUID = 792969002851591180L;

    public BailoutAndRestartBackendException(String msg) {
        super(msg);
    }

    public BailoutAndRestartBackendException(Throwable cause, String msg) {
        super(cause, msg);
    }
}

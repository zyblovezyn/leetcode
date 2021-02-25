/*
 * Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.arraycopy;

import org.graalvm.compiler.core.common.spi.ForeignCallDescriptor;
import org.graalvm.compiler.core.common.spi.ForeignCallSignature;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.LocationIdentity;

import jdk.vm.ci.meta.JavaKind;

public interface ArrayCopyLookup {

    ForeignCallSignature UNSAFE_ARRAYCOPY = new ForeignCallSignature("unsafe_arraycopy", void.class, Word.class, Word.class, Word.class);
    ForeignCallSignature GENERIC_ARRAYCOPY = new ForeignCallSignature("generic_arraycopy", int.class, Word.class, int.class, Word.class, int.class, int.class);

    ForeignCallDescriptor lookupCheckcastArraycopyDescriptor(boolean uninit);

    ForeignCallDescriptor lookupArraycopyDescriptor(JavaKind kind, boolean aligned, boolean disjoint, boolean uninit, LocationIdentity killedLocation);
}

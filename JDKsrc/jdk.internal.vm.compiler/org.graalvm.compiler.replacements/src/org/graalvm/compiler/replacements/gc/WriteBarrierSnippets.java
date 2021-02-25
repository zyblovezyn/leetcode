/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.replacements.gc;

import org.graalvm.compiler.nodes.NamedLocationIdentity;
import org.graalvm.compiler.nodes.PiNode;
import org.graalvm.compiler.nodes.SnippetAnchorNode;
import org.graalvm.compiler.nodes.memory.address.AddressNode.Address;
import org.graalvm.compiler.replacements.nodes.AssertionNode;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.LocationIdentity;
import jdk.internal.vm.compiler.word.WordFactory;

public abstract class WriteBarrierSnippets {
    public static final LocationIdentity GC_CARD_LOCATION = NamedLocationIdentity.mutable("GC-Card");

    protected static void verifyNotArray(Object object) {
        if (object != null) {
            // Manually build the null check and cast because we're in snippet that's lowered late.
            AssertionNode.dynamicAssert(!PiNode.piCastNonNull(object, SnippetAnchorNode.anchor()).getClass().isArray(), "imprecise card mark used with array");
        }
    }

    protected static Word getPointerToFirstArrayElement(Address address, int length, int elementStride) {
        long result = Word.fromAddress(address).rawValue();
        if (elementStride < 0) {
            // the address points to the place after the last array element
            result = result + elementStride * length;
        }
        return WordFactory.unsigned(result);
    }

    protected static Word getPointerToLastArrayElement(Address address, int length, int elementStride) {
        long result = Word.fromAddress(address).rawValue();
        if (elementStride < 0) {
            // the address points to the place after the last array element
            result = result + elementStride;
        } else {
            result = result + (length - 1) * elementStride;
        }
        return WordFactory.unsigned(result);
    }
}

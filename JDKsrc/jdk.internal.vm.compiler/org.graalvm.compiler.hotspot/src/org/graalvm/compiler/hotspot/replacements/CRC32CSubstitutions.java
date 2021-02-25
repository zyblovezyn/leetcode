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


package org.graalvm.compiler.hotspot.replacements;

import static org.graalvm.compiler.hotspot.GraalHotSpotVMConfig.INJECTED_METAACCESS;
import static org.graalvm.compiler.hotspot.meta.HotSpotForeignCallDescriptor.Reexecutability.NOT_REEXECUTABLE;
import static org.graalvm.compiler.hotspot.meta.HotSpotForeignCallDescriptor.Transition.LEAF_NO_VZERO;
import static jdk.internal.vm.compiler.word.LocationIdentity.any;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.core.common.spi.ForeignCallDescriptor;
import org.graalvm.compiler.graph.Node.ConstantNodeParameter;
import org.graalvm.compiler.graph.Node.NodeIntrinsic;
import org.graalvm.compiler.hotspot.meta.HotSpotForeignCallDescriptor;
import org.graalvm.compiler.nodes.ComputeObjectAddressNode;
import org.graalvm.compiler.nodes.extended.ForeignCallNode;
import org.graalvm.compiler.replacements.ReplacementsUtil;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.WordBase;
import jdk.internal.vm.compiler.word.WordFactory;

import jdk.vm.ci.meta.JavaKind;

// JaCoCo Exclude

/**
 * Substitutions for java.util.zip.CRC32C.
 */
@ClassSubstitution(className = "java.util.zip.CRC32C", optional = true)
public class CRC32CSubstitutions {

    @MethodSubstitution
    static int updateBytes(int crc, byte[] b, int off, int end) {
        Word bufAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(b, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte) + off));
        return updateBytesCRC32(UPDATE_BYTES_CRC32C, crc, bufAddr, end - off);
    }

    @MethodSubstitution
    static int updateDirectByteBuffer(int crc, long addr, int off, int end) {
        WordBase bufAddr = WordFactory.unsigned(addr).add(off);
        return updateBytesCRC32(UPDATE_BYTES_CRC32C, crc, bufAddr, end - off);
    }

    public static final HotSpotForeignCallDescriptor UPDATE_BYTES_CRC32C = new HotSpotForeignCallDescriptor(LEAF_NO_VZERO, NOT_REEXECUTABLE, any(), "updateBytesCRC32C", int.class, int.class,
                    WordBase.class, int.class);

    @NodeIntrinsic(ForeignCallNode.class)
    public static native int updateBytesCRC32(@ConstantNodeParameter ForeignCallDescriptor descriptor, int crc, WordBase buf, int length);
}

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

import java.util.zip.CRC32;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.core.common.spi.ForeignCallDescriptor;
import org.graalvm.compiler.graph.Node.ConstantNodeParameter;
import org.graalvm.compiler.graph.Node.NodeIntrinsic;
import org.graalvm.compiler.hotspot.meta.HotSpotForeignCallDescriptor;
import org.graalvm.compiler.hotspot.nodes.GraalHotSpotVMConfigNode;
import org.graalvm.compiler.nodes.ComputeObjectAddressNode;
import org.graalvm.compiler.nodes.extended.ForeignCallNode;
import org.graalvm.compiler.replacements.ReplacementsUtil;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.Pointer;
import jdk.internal.vm.compiler.word.WordBase;
import jdk.internal.vm.compiler.word.WordFactory;

import jdk.vm.ci.meta.JavaKind;

// JaCoCo Exclude

/**
 * Substitutions for {@link CRC32}.
 */
@ClassSubstitution(CRC32.class)
public class CRC32Substitutions {

    /**
     * Removed in 9.
     */
    @MethodSubstitution(optional = true)
    static int update(int crc, int b) {
        final Pointer crcTableRawAddress = WordFactory.pointer(GraalHotSpotVMConfigNode.crcTableAddress());

        int c = ~crc;
        int index = (b ^ c) & 0xFF;
        int offset = index << 2;
        int result = crcTableRawAddress.readInt(offset);
        result = result ^ (c >>> 8);
        return ~result;
    }

    /**
     * Removed in 9.
     */
    @MethodSubstitution(optional = true)
    static int updateBytes(int crc, byte[] buf, int off, int len) {
        Word bufAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(buf, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte) + off));
        return updateBytesCRC32(UPDATE_BYTES_CRC32, crc, bufAddr, len);
    }

    /**
     * @since 9
     */
    @MethodSubstitution(optional = true)
    static int updateBytes0(int crc, byte[] buf, int off, int len) {
        Word bufAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(buf, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte) + off));
        return updateBytesCRC32(UPDATE_BYTES_CRC32, crc, bufAddr, len);
    }

    /**
     * Removed in 9.
     */
    @MethodSubstitution(optional = true)
    static int updateByteBuffer(int crc, long addr, int off, int len) {
        WordBase bufAddr = WordFactory.unsigned(addr).add(off);
        return updateBytesCRC32(UPDATE_BYTES_CRC32, crc, bufAddr, len);
    }

    /**
     * @since 9
     */
    @MethodSubstitution(optional = true)
    static int updateByteBuffer0(int crc, long addr, int off, int len) {
        WordBase bufAddr = WordFactory.unsigned(addr).add(off);
        return updateBytesCRC32(UPDATE_BYTES_CRC32, crc, bufAddr, len);
    }

    public static final HotSpotForeignCallDescriptor UPDATE_BYTES_CRC32 = new HotSpotForeignCallDescriptor(LEAF_NO_VZERO, NOT_REEXECUTABLE, any(), "updateBytesCRC32", int.class, int.class,
                    WordBase.class, int.class);

    @NodeIntrinsic(ForeignCallNode.class)
    public static native int updateBytesCRC32(@ConstantNodeParameter ForeignCallDescriptor descriptor, int crc, WordBase buf, int length);
}

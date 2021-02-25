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


package org.graalvm.compiler.hotspot.replacements;

import static org.graalvm.compiler.hotspot.GraalHotSpotVMConfig.INJECTED_INTRINSIC_CONTEXT;
import static org.graalvm.compiler.hotspot.GraalHotSpotVMConfig.INJECTED_METAACCESS;
import static org.graalvm.compiler.hotspot.replacements.CipherBlockChainingSubstitutions.aesCryptType;
import static org.graalvm.compiler.hotspot.replacements.CipherBlockChainingSubstitutions.embeddedCipherOffset;
import static org.graalvm.compiler.nodes.PiNode.piCastNonNull;
import static org.graalvm.compiler.nodes.java.InstanceOfNode.doInstanceof;

import org.graalvm.compiler.api.replacements.ClassSubstitution;
import org.graalvm.compiler.api.replacements.Fold;
import org.graalvm.compiler.api.replacements.Fold.InjectedParameter;
import org.graalvm.compiler.api.replacements.MethodSubstitution;
import org.graalvm.compiler.hotspot.HotSpotBackend;
import org.graalvm.compiler.nodes.ComputeObjectAddressNode;
import org.graalvm.compiler.nodes.extended.RawLoadNode;
import org.graalvm.compiler.nodes.graphbuilderconf.IntrinsicContext;
import org.graalvm.compiler.replacements.ReplacementsUtil;
import org.graalvm.compiler.word.Word;
import jdk.internal.vm.compiler.word.LocationIdentity;
import jdk.internal.vm.compiler.word.WordFactory;

import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.ResolvedJavaType;

@ClassSubstitution(className = "com.sun.crypto.provider.CounterMode", optional = true)
public class CounterModeSubstitutions {

    @MethodSubstitution(isStatic = false)
    static int implCrypt(Object receiver, byte[] in, int inOff, int len, byte[] out, int outOff) {
        Object realReceiver = piCastNonNull(receiver, HotSpotReplacementsUtil.methodHolderClass(INJECTED_INTRINSIC_CONTEXT));
        Object embeddedCipher = RawLoadNode.load(realReceiver, embeddedCipherOffset(INJECTED_INTRINSIC_CONTEXT), JavaKind.Object, LocationIdentity.any());
        if (doInstanceof(aesCryptType(INJECTED_INTRINSIC_CONTEXT), embeddedCipher)) {
            Object aesCipher = piCastNonNull(embeddedCipher, aesCryptType(INJECTED_INTRINSIC_CONTEXT));

            Word srcAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(in, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte) + inOff));
            Word dstAddr = WordFactory.unsigned(ComputeObjectAddressNode.get(out, ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte) + outOff));
            Word usedPtr = WordFactory.unsigned(ComputeObjectAddressNode.get(realReceiver, usedOffset(INJECTED_INTRINSIC_CONTEXT)));

            int cntOffset = counterOffset(INJECTED_INTRINSIC_CONTEXT);
            int encCntOffset = encCounterOffset(INJECTED_INTRINSIC_CONTEXT);
            Object kObject = RawLoadNode.load(aesCipher, AESCryptSubstitutions.kOffset(INJECTED_INTRINSIC_CONTEXT), JavaKind.Object, LocationIdentity.any());
            Object cntObj = RawLoadNode.load(realReceiver, cntOffset, JavaKind.Object, LocationIdentity.any());
            Object encCntObj = RawLoadNode.load(realReceiver, encCntOffset, JavaKind.Object, LocationIdentity.any());

            Word kPtr = Word.objectToTrackedPointer(kObject).add(ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Int));
            Word cntPtr = Word.objectToTrackedPointer(cntObj).add(ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte));
            Word encCntPtr = Word.objectToTrackedPointer(encCntObj).add(ReplacementsUtil.getArrayBaseOffset(INJECTED_METAACCESS, JavaKind.Byte));

            return HotSpotBackend.counterModeAESCrypt(srcAddr, dstAddr, kPtr, cntPtr, len, encCntPtr, usedPtr);
        } else {
            return implCrypt(realReceiver, in, inOff, len, out, outOff);
        }
    }

    static ResolvedJavaType counterModeType(IntrinsicContext context) {
        return HotSpotReplacementsUtil.getType(context, "Lcom/sun/crypto/provider/CounterMode;");
    }

    @Fold
    static int counterOffset(@InjectedParameter IntrinsicContext context) {
        return HotSpotReplacementsUtil.getFieldOffset(counterModeType(context), "counter");
    }

    @Fold
    static int encCounterOffset(@InjectedParameter IntrinsicContext context) {
        return HotSpotReplacementsUtil.getFieldOffset(counterModeType(context), "encryptedCounter");
    }

    @Fold
    static int usedOffset(@InjectedParameter IntrinsicContext context) {
        return HotSpotReplacementsUtil.getFieldOffset(counterModeType(context), "used");
    }
}

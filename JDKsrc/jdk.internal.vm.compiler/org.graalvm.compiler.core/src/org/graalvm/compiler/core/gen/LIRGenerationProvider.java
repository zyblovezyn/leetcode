/*
 * Copyright (c) 2017, 2019, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.compiler.core.gen;

import org.graalvm.compiler.code.CompilationResult;
import org.graalvm.compiler.core.common.CompilationIdentifier;
import org.graalvm.compiler.core.common.alloc.RegisterAllocationConfig;
import org.graalvm.compiler.lir.LIR;
import org.graalvm.compiler.lir.asm.CompilationResultBuilder;
import org.graalvm.compiler.lir.asm.CompilationResultBuilderFactory;
import org.graalvm.compiler.lir.framemap.FrameMap;
import org.graalvm.compiler.lir.gen.LIRGenerationResult;
import org.graalvm.compiler.lir.gen.LIRGeneratorTool;
import org.graalvm.compiler.nodes.StructuredGraph;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;

import jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * Provides compiler backend-specific generation helpers for the {@link LIRCompilerBackend}.
 */
public interface LIRGenerationProvider {
    LIRGeneratorTool newLIRGenerator(LIRGenerationResult lirGenRes);

    LIRGenerationResult newLIRGenerationResult(CompilationIdentifier compilationId,
                    LIR lir,
                    RegisterAllocationConfig registerAllocationConfig,
                    StructuredGraph graph,
                    Object stub);

    NodeLIRBuilderTool newNodeLIRBuilder(StructuredGraph graph, LIRGeneratorTool lirGen);

    /**
     * Creates the object used to fill in the details of a given compilation result.
     */
    CompilationResultBuilder newCompilationResultBuilder(LIRGenerationResult lirGenResult,
                    FrameMap frameMap,
                    CompilationResult compilationResult,
                    CompilationResultBuilderFactory factory);

    /**
     * Emits the code for a given graph.
     *
     * @param installedCodeOwner the method the compiled code will be associated with once
     *            installed. This argument can be null.
     */
    void emitCode(CompilationResultBuilder crb, LIR lir, ResolvedJavaMethod installedCodeOwner);
}

/*
 * Copyright (c) 2005, 2019, Oracle and/or its affiliates. All rights reserved.
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
 *
 *
 */

package com.sun.source.tree;

/**
 * A tree node for an {@code instanceof} expression.
 *
 * For example:
 * <pre>
 *   <em>expression</em> instanceof <em>type</em>
 * </pre>
 *
 * @jls 15.20.2 Type Comparison Operator instanceof
 *
 * @author Peter von der Ah&eacute;
 * @author Jonathan Gibbons
 * @since 1.6
 */
public interface InstanceOfTree extends ExpressionTree {
    /**
     * Returns the expression to be tested.
     * @return the expression
     */
    ExpressionTree getExpression();

    /**
     * Returns the type for which to check.
     * @return the type
     */
    Tree getType();

    /**
     * {@preview Associated with pattern matching for instanceof, a preview feature of
     *           the Java language.
     *
     *           This method is associated with <i>pattern matching for instanceof</i>, a preview
     *           feature of the Java language. Preview features
     *           may be removed in a future release, or upgraded to permanent
     *           features of the Java language.}
     *
     * Returns the tested pattern, or null if this instanceof does not use
     * a pattern.
     *
     * <p>For instanceof with a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>type</em> <em>variable name</em>
     * </pre>
     * returns the pattern.
     *
     * <p>For instanceof without a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>type</em>
     * </pre>
     * returns null.
     *
     * @return the tested pattern, or null if this instanceof does not use a pattern.
     * @since 14
     */
    PatternTree getPattern();
}

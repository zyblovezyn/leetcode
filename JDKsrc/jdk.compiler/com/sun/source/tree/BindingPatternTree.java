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
 *
 *
 */

package com.sun.source.tree;

import javax.lang.model.element.Name;

/**
 * {@preview Associated with pattern matching for instanceof, a preview feature of
 *           the Java language.
 *
 *           This interface is associated with <i>pattern matching for instanceof</i>, a preview
 *           feature of the Java language. Preview features
 *           may be removed in a future release, or upgraded to permanent
 *           features of the Java language.}
 *
 * A binding pattern tree
 *
 * @since 14
 */
public interface BindingPatternTree extends PatternTree {

    /**
     * Returns the type of the bind variable.
     * @return the type
     */
    Tree getType();

    /**
     * A binding variable name.
     * @return the name of the binding variable
     */
    Name getBinding();

}


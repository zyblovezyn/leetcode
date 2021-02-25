/*
 * Copyright (c) 2005, 2020, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Collections;
import java.util.List;
import javax.lang.model.element.Name;

/**
 * A tree node for a class, interface, enum, record, or annotation
 * type declaration.
 *
 * For example:
 * <pre>
 *   <em>modifiers</em> class <em>simpleName</em> <em>typeParameters</em>
 *       extends <em>extendsClause</em>
 *       implements <em>implementsClause</em>
 *   {
 *       <em>members</em>
 *   }
 * </pre>
 *
 * @jls 8.1 Class Declarations
 * @jls 8.9 Enum Types
 * @jls 8.10 Record Types
 * @jls 9.1 Interface Declarations
 * @jls 9.6 Annotation Types
 *
 * @author Peter von der Ah&eacute;
 * @author Jonathan Gibbons
 * @since 1.6
 */
public interface ClassTree extends StatementTree {
    /**
     * Returns the modifiers, including any annotations,
     * for this type declaration.
     * @return the modifiers
     */
    ModifiersTree getModifiers();

    /**
     * Returns the simple name of this type declaration.
     * @return the simple name
     */
    Name getSimpleName();

    /**
     * Returns any type parameters of this type declaration.
     * @return the type parameters
     */
    List<? extends TypeParameterTree> getTypeParameters();

    /**
     * Returns the supertype of this type declaration,
     * or {@code null} if none is provided.
     * @return the supertype
     */
    Tree getExtendsClause();

    /**
     * Returns the interfaces implemented by this type declaration.
     * @return the interfaces
     */
    List<? extends Tree> getImplementsClause();

    /**
     * {@preview Associated with sealed classes, a preview feature of the Java language.
     *
     *           This method is associated with <i>sealed classes</i>, a preview
     *           feature of the Java language. Preview features
     *           may be removed in a future release, or upgraded to permanent
     *           features of the Java language.}
     *
     * Returns the subclasses permitted by this type declaration.
     *
     * @implSpec this implementation returns an empty list
     *
     * @return the subclasses
     *
     * @since 15
     */
    @jdk.internal.PreviewFeature(feature=jdk.internal.PreviewFeature.Feature.SEALED_CLASSES,
                                             essentialAPI=false)
    default List<? extends Tree> getPermitsClause() {
        return List.of();
    }

    /**
     * Returns the members declared in this type declaration.
     * @return the members
     */
    List<? extends Tree> getMembers();
}

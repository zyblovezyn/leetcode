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

package javax.lang.model.element;

/**
 * The {@code kind} of an element.
 *
 * <p>Note that it is possible additional element kinds will be added
 * to accommodate new, currently unknown, language structures added to
 * future versions of the Java programming language.
 *
 * @author Joseph D. Darcy
 * @author Scott Seligman
 * @author Peter von der Ah&eacute;
 * @see Element
 * @since 1.6
 */
public enum ElementKind {

    /** A package. */
    PACKAGE,

    // Declared types
    /** An enum type. */
    ENUM,
    /**
     * A class not described by a more specific kind (like {@code
     * ENUM} or {@code RECORD}).
     */
    CLASS,

    /** An annotation type. */
    ANNOTATION_TYPE,
    /**
     * An interface not described by a more specific kind (like
     * {@code ANNOTATION_TYPE}).
     */
    INTERFACE,

    // Variables
    /** An enum constant. */
    ENUM_CONSTANT,
    /**
     * A field not described by a more specific kind (like
     * {@code ENUM_CONSTANT}).
     */
    FIELD,
    /** A parameter of a method or constructor. */
    PARAMETER,
    /** A local variable. */
    LOCAL_VARIABLE,
    /** A parameter of an exception handler. */
    EXCEPTION_PARAMETER,

    // Executables
    /** A method. */
    METHOD,
    /** A constructor. */
    CONSTRUCTOR,
    /** A static initializer. */
    STATIC_INIT,
    /** An instance initializer. */
    INSTANCE_INIT,

    /** A type parameter. */
    TYPE_PARAMETER,

    /**
     * An implementation-reserved element.  This is not the element
     * you are looking for.
     */
    OTHER,

    // Constants added since initial release

    /**
     * A resource variable.
     * @since 1.7
     */
     RESOURCE_VARIABLE,

    /**
     * A module.
     * @since 9
     * @spec JPMS
     */
     MODULE,

    /**
     * {@preview Associated with records, a preview feature of the Java language.
     *
     *           This enum constant is associated with <i>records</i>, a preview
     *           feature of the Java language. Preview features
     *           may be removed in a future release, or upgraded to permanent
     *           features of the Java language.}
     *
     * A record type.
     * @since 14
     */
    @jdk.internal.PreviewFeature(feature=jdk.internal.PreviewFeature.Feature.RECORDS,
                                 essentialAPI=false)
    RECORD,

    /**
     * {@preview Associated with records, a preview feature of the Java language.
     *
     *           This enum constant is associated with <i>records</i>, a preview
     *           feature of the Java language. Preview features
     *           may be removed in a future release, or upgraded to permanent
     *           features of the Java language.}
     *
     * A record component of a {@code record}.
     * @since 14
     */
    @jdk.internal.PreviewFeature(feature=jdk.internal.PreviewFeature.Feature.RECORDS,
                                 essentialAPI=false)
    RECORD_COMPONENT,

    /**
     * {@preview Associated with pattern matching for {@code
     * instanceof}, a preview feature of the Java language.
     *
     *           This enum constant is associated with <i>pattern
     *           matching for {@code instanceof}</i>, a preview
     *           feature of the Java language. Preview features
     *           may be removed in a future release, or upgraded to permanent
     *           features of the Java language.}
     *
     * A binding variable in a pattern .
     * @since 14
     */
    @jdk.internal.PreviewFeature(feature=jdk.internal.PreviewFeature.Feature.PATTERN_MATCHING_IN_INSTANCEOF,
                                 essentialAPI=false)
    BINDING_VARIABLE;

    /**
     * Returns {@code true} if this is a kind of class:
     * either {@code CLASS} or {@code ENUM} or {@code RECORD}.
     *
     * @return {@code true} if this is a kind of class
     */
    @SuppressWarnings("preview")
    public boolean isClass() {
        return this == CLASS || this == ENUM || this == RECORD;
    }

    /**
     * Returns {@code true} if this is a kind of interface:
     * either {@code INTERFACE} or {@code ANNOTATION_TYPE}.
     *
     * @return {@code true} if this is a kind of interface
     */
    public boolean isInterface() {
        return this == INTERFACE || this == ANNOTATION_TYPE;
    }

    /**
     * Returns {@code true} if this is a kind of field:
     * either {@code FIELD} or {@code ENUM_CONSTANT}.
     *
     * @return {@code true} if this is a kind of field
     */
    public boolean isField() {
        return this == FIELD || this == ENUM_CONSTANT;
    }
}

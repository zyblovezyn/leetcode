/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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
 * {@preview Associated with records, a preview feature of the Java language.
 *
 *           This class is associated with <i>records</i>, a preview
 *           feature of the Java language. Preview features
 *           may be removed in a future release, or upgraded to permanent
 *           features of the Java language.}
 *
 * Represents a record component.
 *
 * @since 14
 */
@jdk.internal.PreviewFeature(feature=jdk.internal.PreviewFeature.Feature.RECORDS,
                             essentialAPI=false)
public interface RecordComponentElement extends Element {
    /**
     * Returns the enclosing element of this record component.
     *
     * The enclosing element of a record component is the type
     * declaring the record component.
     *
     * @return the enclosing element of this record component
     */
    @Override
    Element getEnclosingElement();

    /**
     * Returns the simple name of this record component.
     *
     * <p>The name of each record component must be distinct from the
     * names of all other record components.
     *
     * @return the simple name of this record component
     *
     * @jls 6.2 Names and Identifiers
     */
    @Override
    Name getSimpleName();

    /**
     * Returns the executable element for the accessor associated with the
     * given record component.
     *
     * @return the record component accessor.
     */
    ExecutableElement getAccessor();
}

/*
 * Copyright (c) 2003, 2020, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.doclets.toolkit.taglets;

import java.util.Set;
import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import jdk.javadoc.doclet.Taglet.Location;
import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 * This is the Taglet interface used internally within the doclet.
 */

public interface Taglet {
    /**
     * Returns the set of allowed locations for block tags
     * handled by this taglet.
     *
     * @return the set of allowable locations
     */
    Set<Location> getAllowedLocations();

    /**
     * Return true if this <code>Taglet</code>
     * is used in field documentation.
     * @return true if this <code>Taglet</code>
     * is used in field documentation and false
     * otherwise.
     */
    boolean inField();

    /**
     * Return true if this <code>Taglet</code>
     * is used in constructor documentation.
     * @return true if this <code>Taglet</code>
     * is used in constructor documentation and false
     * otherwise.
     */
    boolean inConstructor();

    /**
     * Return true if this <code>Taglet</code>
     * is used in method documentation.
     * @return true if this <code>Taglet</code>
     * is used in method documentation and false
     * otherwise.
     */
    boolean inMethod();

    /**
     * Return true if this <code>Taglet</code>
     * is used in overview documentation.
     * @return true if this <code>Taglet</code>
     * is used in method documentation and false
     * otherwise.
     */
    boolean inOverview();

    /**
     * Return true if this <code>Taglet</code>
     * is used in module documentation.
     * @return true if this <code>Taglet</code>
     * is used in module documentation and false
     * otherwise.
     */
    boolean inModule();

    /**
     * Return true if this <code>Taglet</code>
     * is used in package documentation.
     * @return true if this <code>Taglet</code>
     * is used in package documentation and false
     * otherwise.
     */
    boolean inPackage();

    /**
     * Return true if this <code>Taglet</code>
     * is used in type documentation (classes or
     * interfaces).
     * @return true if this <code>Taglet</code>
     * is used in type documentation and false
     * otherwise.
     */
    boolean inType();

    /**
     * Return true if this <code>Taglet</code>
     * is an inline tag. Return false otherwise.
     * @return true if this <code>Taglet</code>
     * is an inline tag and false otherwise.
     */
    boolean isInlineTag();

    /**
     * Indicates whether this taglet supports block tags.
     *
     * @return true if this taglet supports block tags
     * @implSpec This implementation returns the inverse
     * result to {@code isInlineTag}.
     */
    default boolean isBlockTag() {
        return !isInlineTag();
    }

    /**
     * Return the name of this custom tag.
     * @return the name of this custom tag.
     */
    String getName();

    /**
     * Returns the content to be included in the generated output for an
     * instance of a tag handled by this taglet.
     *
     * @param element the element for the enclosing doc comment
     * @param tag     the tag
     * @param writer  the taglet-writer used in this doclet
     * @return the output for this tag
     * @throws UnsupportedTagletOperationException thrown when the method is not supported by the taglet
     */
    Content getTagletOutput(Element element, DocTree tag, TagletWriter writer) throws
            UnsupportedTagletOperationException;

    /**
     * Returns the content to be included in the generated output for all
     * instances of tags handled by this taglet.
     *
     * @param element the element for the enclosing doc comment
     * @param writer  the taglet-writer used in this doclet
     * @return the output for this tag
     * @throws UnsupportedTagletOperationException thrown when the method is not supported by the taglet
     */
    Content getTagletOutput(Element element, TagletWriter writer) throws
            UnsupportedTagletOperationException;

    class UnsupportedTagletOperationException extends UnsupportedOperationException {
        private static final long serialVersionUID = -3530273193380250271L;
        public UnsupportedTagletOperationException(String message) {
            super(message);
        }
    }
}

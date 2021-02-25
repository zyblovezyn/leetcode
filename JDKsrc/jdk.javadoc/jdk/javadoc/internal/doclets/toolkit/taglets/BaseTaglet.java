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
import com.sun.source.doctree.UnknownBlockTagTree;
import jdk.javadoc.doclet.Taglet.Location;
import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 * A base class that implements the {@link Taglet} interface.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class BaseTaglet implements Taglet {

    protected final DocTree.Kind tagKind;
    protected final String name;
    private final boolean inline;
    private final Set<Location> sites;

    BaseTaglet(DocTree.Kind tagKind, boolean inline, Set<Location> sites) {
        this.tagKind = tagKind;
        this.name = tagKind.tagName;
        this.inline = inline;
        this.sites = sites;
    }

    BaseTaglet(String name, boolean inline, Set<Location> sites) {
        this.tagKind = inline ? DocTree.Kind.UNKNOWN_INLINE_TAG : DocTree.Kind.UNKNOWN_BLOCK_TAG;
        this.name = name;
        this.inline = inline;
        this.sites = sites;
    }

    @Override
    public Set<Location> getAllowedLocations() {
        return sites;
    }

    /**
     * Returns true if this {@code Taglet} can be used in constructor documentation.
     *
     * @return true if this {@code Taglet} can be used in constructor documentation and false
     * otherwise.
     */
    @Override
    public final boolean inConstructor() {
        return sites.contains(Location.CONSTRUCTOR);
    }

    /**
     * Returns true if this {@code Taglet} can be used in field documentation.
     *
     * @return true if this {@code Taglet} can be used in field documentation and false
     * otherwise.
     */
    @Override
    public final boolean inField() {
        return sites.contains(Location.FIELD);
    }

    /**
     * Returns true if this {@code Taglet} can be used in method documentation.
     *
     * @return true if this {@code Taglet} can be used in method documentation and false
     * otherwise.
     */
    @Override
    public final boolean inMethod() {
        return sites.contains(Location.METHOD);
    }

    /**
     * Returns true if this {@code Taglet} can be used in overview documentation.
     *
     * @return true if this {@code Taglet} can be used in method documentation and false
     * otherwise.
     */
    @Override
    public final boolean inOverview() {
        return sites.contains(Location.OVERVIEW);
    }

    /**
     * Returns true if this {@code Taglet} can be used in module documentation.
     *
     * @return true if this {@code Taglet} can be used in module documentation and false
     * otherwise.
     */
    @Override
    public final boolean inModule() {
        return sites.contains(Location.MODULE);
    }

    /**
     * Returns true if this {@code Taglet} can be used in package documentation.
     *
     * @return true if this {@code Taglet} can be used in package documentation and false
     * otherwise.
     */
    @Override
    public final boolean inPackage() {
        return sites.contains(Location.PACKAGE);
    }

    /**
     * Returns true if this {@code Taglet} can be used in type documentation (classes or interfaces).
     *
     * @return true if this {@code Taglet} can be used in type documentation and false
     * otherwise.
     */
    @Override
    public final boolean inType() {
        return sites.contains(Location.TYPE);
    }

    /**
     * Returns true if this {@code Taglet} is an inline tag.
     *
     * @return true if this {@code Taglet} represents an inline tag and false otherwise.
     */
    @Override
    public final boolean isInlineTag() {
        return inline;
    }

    /**
     * Returns the kind of trees recognized by this taglet.
     *
     * @return the kind of trees recognized by this taglet
     */
    public DocTree.Kind getTagKind() {
        return tagKind;
    }

    /**
     * Returns the name of this tag.
     *
     * @return the name of this tag.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns whether or not this taglet accepts a {@code DocTree} node.
     * The taglet accepts a tree node if it has the same kind, and
     * if the kind is {@code UNKNOWN_BLOCK_TAG} the same tag name.
     *
     * @param tree the tree node
     * @return {@code true} if this taglet accepts this tree node.
     */
    public boolean accepts(DocTree tree) {
        return (tree.getKind() == DocTree.Kind.UNKNOWN_BLOCK_TAG
                    && tagKind == DocTree.Kind.UNKNOWN_BLOCK_TAG)
                ? ((UnknownBlockTagTree) tree).getTagName().equals(name)
                : tree.getKind() == tagKind;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation throws {@link UnsupportedTagletOperationException}.
     */
    @Override
    public Content getTagletOutput(Element element, DocTree tag, TagletWriter writer) {
        throw new UnsupportedTagletOperationException("Method not supported in taglet " + getName() + ".");
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation throws {@link UnsupportedTagletOperationException}
     */
    @Override
    public Content getTagletOutput(Element holder, TagletWriter writer) {
        throw new UnsupportedTagletOperationException("Method not supported in taglet " + getName() + ".");
    }
}

/*
 * Copyright (c) 2010, 2020, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.internal.doclets.formats.html.markup;

import java.io.IOException;
import java.io.Writer;

import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.util.DocletConstants;

/**
 * Class for generating string content for HTML tags of javadoc output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class StringContent extends Content {

    private final StringBuilder stringContent;

    /**
     * Constructor to construct StringContent object.
     */
    public StringContent() {
        stringContent = new StringBuilder();
    }

    /**
     * Constructor to construct StringContent object with some initial content.
     *
     * @param initialContent initial content for the object
     */
    public StringContent(CharSequence initialContent) {
        stringContent = new StringBuilder();
        Entity.escapeHtmlChars(initialContent, stringContent);
    }

    /**
     * Adds content for the StringContent object.  The method escapes
     * HTML characters for the string content that is added.
     *
     * @param strContent string content to be added
     */
    @Override
    public StringContent add(CharSequence strContent) {
        Entity.escapeHtmlChars(strContent, stringContent);
        return this;
    }

    @Override
    public boolean isEmpty() {
        return (stringContent.length() == 0);
    }

    @Override
    public int charCount() {
        return RawHtml.charCount(stringContent.toString());
    }

    @Override
    public String toString() {
        return stringContent.toString();
    }

    @Override
    public boolean write(Writer out, boolean atNewline) throws IOException {
        String s = stringContent.toString();
        out.write(s);
        return s.endsWith(DocletConstants.NL);
    }
}

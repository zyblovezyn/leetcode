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
 *
 *
 */

package jdk.javadoc.internal.doclets.formats.html;

import jdk.javadoc.internal.doclets.formats.html.markup.BodyContents;
import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.Navigation.PageMode;
import jdk.javadoc.internal.doclets.formats.html.markup.RawHtml;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;

/**
 * Abstract class to generate the overview files.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 */
public abstract class AbstractOverviewIndexWriter extends HtmlDocletWriter {

    protected Navigation navBar;

    /**
     * Constructs the AbstractOverviewIndexWriter.
     *
     * @param configuration  The current configuration
     * @param filename Name of the module index file to be generated.
     */
    public AbstractOverviewIndexWriter(HtmlConfiguration configuration,
                                      DocPath filename) {
        super(configuration, filename);
        this.navBar = new Navigation(null, configuration, PageMode.OVERVIEW, path);
    }

    /**
     * Adds the top text (from the -top option), the upper
     * navigation bar, and then the title (from the"-header"
     * option), at the top of page.
     *
     * @param header the documentation tree to which the navigation bar header will be added
     */
    protected void addNavigationBarHeader(Content header) {
        addTop(header);
        navBar.setUserHeader(getUserHeaderFooter(true));
        header.add(navBar.getContent(Navigation.Position.TOP));
    }

    /**
     * Adds the lower navigation bar and the bottom text
     * (from the -bottom option) at the bottom of page.
     *
     * @param footer the documentation tree to which the navigation bar footer will be added
     */
    protected void addNavigationBarFooter(Content footer) {
        navBar.setUserFooter(getUserHeaderFooter(false));
        footer.add(navBar.getContent(Navigation.Position.BOTTOM));
        addBottom(footer);
    }

    /**
     * Adds the overview summary comment for this documentation. Add one line
     * summary at the top of the page and generate a link to the description,
     * which is added at the end of this page.
     *
     * @param main the documentation tree to which the overview header will be added
     */
    protected void addOverviewHeader(Content main) {
        addConfigurationTitle(main);
        if (!utils.getFullBody(configuration.overviewElement).isEmpty()) {
            addOverviewComment(main);
        }
    }

    /**
     * Adds the overview comment as provided in the file specified by the
     * "-overview" option on the command line.
     *
     * @param htmltree the documentation tree to which the overview comment will
     *                 be added
     */
    protected void addOverviewComment(Content htmltree) {
        if (!utils.getFullBody(configuration.overviewElement).isEmpty()) {
            addInlineComment(configuration.overviewElement, htmltree);
        }
    }

    /**
     * Generate and prints the contents in the index file.
     *
     * @param title the title of the window
     * @param description the content for the description META tag
     * @throws DocFileIOException if there is a problem building the package index file
     */
    protected void buildOverviewIndexFile(String title, String description)
            throws DocFileIOException {
        String windowOverview = resources.getText(title);
        Content body = getBody(getWindowTitle(windowOverview));
        Content header = new ContentBuilder();
        addNavigationBarHeader(header);
        Content main = new ContentBuilder();
        addOverviewHeader(main);
        addIndex(main);
        Content footer = HtmlTree.FOOTER();
        addNavigationBarFooter(footer);
        body.add(new BodyContents()
                .setHeader(header)
                .addMainContent(main)
                .setFooter(footer));
        printHtmlDocument(
                configuration.metakeywords.getOverviewMetaKeywords(title, configuration.getOptions().docTitle()),
                description, body);
    }

    /**
     * Adds the index to the documentation tree.
     *
     * @param main the document tree to which the packages/modules list will be added
     */
    protected abstract void addIndex(Content main);

    /**
     * Adds the doctitle to the documentation tree, if it is specified on the command line.
     *
     * @param body the document tree to which the title will be added
     */
    protected void addConfigurationTitle(Content body) {
        String doctitle = configuration.getOptions().docTitle();
        if (!doctitle.isEmpty()) {
            Content title = new RawHtml(doctitle);
            Content heading = HtmlTree.HEADING(Headings.PAGE_TITLE_HEADING,
                    HtmlStyle.title, title);
            Content div = HtmlTree.DIV(HtmlStyle.header, heading);
            body.add(div);
        }
    }
}

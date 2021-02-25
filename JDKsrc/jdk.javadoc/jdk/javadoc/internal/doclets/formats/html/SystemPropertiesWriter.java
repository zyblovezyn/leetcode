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

import jdk.javadoc.internal.doclets.formats.html.SearchIndexItem.Category;
import jdk.javadoc.internal.doclets.formats.html.markup.BodyContents;
import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.FixedStringContent;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.Navigation.PageMode;
import jdk.javadoc.internal.doclets.formats.html.markup.StringContent;
import jdk.javadoc.internal.doclets.formats.html.markup.Table;
import jdk.javadoc.internal.doclets.formats.html.markup.TableHeader;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.DocletElement;
import jdk.javadoc.internal.doclets.toolkit.OverviewElement;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.DocPaths;

import javax.lang.model.element.Element;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.WeakHashMap;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Generates the file with the summary of all the system properties.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class SystemPropertiesWriter extends HtmlDocletWriter {

    private final Navigation navBar;

    /**
     * Cached contents of {@code <title>...</title>} tags of the HTML pages.
     */
    final Map<Element, String> titles = new WeakHashMap<>();

    /**
     * Constructs SystemPropertiesWriter object.
     *
     * @param configuration The current configuration
     * @param filename Path to the file which is getting generated.
     */
    public SystemPropertiesWriter(HtmlConfiguration configuration, DocPath filename) {
        super(configuration, filename);
        this.navBar = new Navigation(null, configuration, PageMode.SYSTEM_PROPERTIES, path);
    }

    public static void generate(HtmlConfiguration configuration) throws DocFileIOException {
        generate(configuration, DocPaths.SYSTEM_PROPERTIES);
    }

    private static void generate(HtmlConfiguration configuration, DocPath fileName) throws DocFileIOException {
        boolean hasSystemProperties = configuration.searchItems
                .containsAnyOfCategories(Category.SYSTEM_PROPERTY);
        if (!hasSystemProperties) {
            // Cannot defer this check any further, because of the super() call
            // that prints out notices on creating files, etc.
            //
            // There is probably a better place for this kind of checks (see how
            // this is achieved in other "optional" pages, like Constant Values
            // and Serialized Form).
            return;
        }
        SystemPropertiesWriter systemPropertiesGen = new SystemPropertiesWriter(configuration, fileName);
        systemPropertiesGen.buildSystemPropertiesPage();
    }

    /**
     * Prints all the system properties to the file.
     */
    protected void buildSystemPropertiesPage() throws DocFileIOException {
        String title = resources.getText("doclet.systemProperties");
        HtmlTree body = getBody(getWindowTitle(title));
        Content headerContent = new ContentBuilder();
        addTop(headerContent);
        navBar.setUserHeader(getUserHeaderFooter(true));
        headerContent.add(navBar.getContent(Navigation.Position.TOP));
        Content mainContent = new ContentBuilder();
        addSystemProperties(mainContent);
        Content footer = HtmlTree.FOOTER();
        navBar.setUserFooter(getUserHeaderFooter(false));
        footer.add(navBar.getContent(Navigation.Position.BOTTOM));
        addBottom(footer);
        body.add(new BodyContents()
                .setHeader(headerContent)
                .addMainContent(HtmlTree.DIV(HtmlStyle.header,
                        HtmlTree.HEADING(Headings.PAGE_TITLE_HEADING,
                                contents.getContent("doclet.systemProperties"))))
                .addMainContent(mainContent)
                .setFooter(footer));
        printHtmlDocument(null, "system properties", body);
    }

    /**
     * Adds all the system properties to the content tree.
     *
     * @param content HtmlTree content to which the links will be added
     */
    protected void addSystemProperties(Content content) {
        Map<String, List<SearchIndexItem>> searchIndexMap = groupSystemProperties();
        Content separator = new StringContent(", ");
        Table table = new Table(HtmlStyle.systemPropertiesSummary, HtmlStyle.summaryTable)
                .setCaption(contents.systemPropertiesSummaryLabel)
                .setHeader(new TableHeader(contents.propertyLabel, contents.referencedIn))
                .setColumnStyles(HtmlStyle.colFirst, HtmlStyle.colLast);
        for (Entry<String, List<SearchIndexItem>> entry : searchIndexMap.entrySet()) {
            Content propertyName = new StringContent(entry.getKey());
            List<SearchIndexItem> searchIndexItems = entry.getValue();
            Content separatedReferenceLinks = new ContentBuilder();
            separatedReferenceLinks.add(createLink(searchIndexItems.get(0)));
            for (int i = 1; i < searchIndexItems.size(); i++) {
                separatedReferenceLinks.add(separator);
                separatedReferenceLinks.add(createLink(searchIndexItems.get(i)));
            }
            table.addRow(propertyName, HtmlTree.DIV(HtmlStyle.block, separatedReferenceLinks));
        }
        content.add(table);
    }

    private Map<String, List<SearchIndexItem>> groupSystemProperties() {
        return searchItems
                .itemsOfCategories(Category.SYSTEM_PROPERTY)
                .collect(groupingBy(SearchIndexItem::getLabel, TreeMap::new, toList()));
    }

    private Content createLink(SearchIndexItem i) {
        assert i.getCategory() == Category.SYSTEM_PROPERTY : i;
        if (i.getElement() != null) {
            if (i.getElement() instanceof OverviewElement) {
                return links.createLink(pathToRoot.resolve(i.getUrl()),
                                        resources.getText("doclet.Overview"));
            }
            DocletElement e = ((DocletElement) i.getElement());
            // Implementations of DocletElement do not override equals and
            // hashCode; putting instances of DocletElement in a map is not
            // incorrect, but might well be inefficient
            String t = titles.computeIfAbsent(i.getElement(), utils::getHTMLTitle);
            if (t.isBlank()) {
                // The user should probably be notified (a warning?) that this
                // file does not have a title
                Path p = Path.of(e.getFileObject().toUri());
                t = p.getFileName().toString();
            }
            ContentBuilder b = new ContentBuilder();
            b.add(HtmlTree.CODE(new FixedStringContent(i.getHolder() + ": ")));
            // non-program elements should be displayed using a normal font
            b.add(t);
            return links.createLink(pathToRoot.resolve(i.getUrl()), b);
        } else {
            // program elements should be displayed using a code font
            Content link = links.createLink(pathToRoot.resolve(i.getUrl()), i.getHolder());
            return HtmlTree.CODE(link);
        }
    }
}

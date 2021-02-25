/*
 * Copyright (c) 2016, 2020, Oracle and/or its affiliates. All rights reserved.
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

import jdk.javadoc.internal.doclets.formats.html.markup.Table;
import jdk.javadoc.internal.doclets.formats.html.markup.TableHeader;

import java.util.*;

import javax.lang.model.element.ModuleElement;

import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.markup.StringContent;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.DocPaths;

/**
 * Generate the module index page "index.html".
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class ModuleIndexWriter extends AbstractOverviewIndexWriter {

    /**
     * Modules to be documented.
     */
    protected SortedSet<ModuleElement> modules;

    /**
     * Construct the ModuleIndexWriter.
     *
     * @param configuration the configuration object
     * @param filename the name of the generated file
     */
    public ModuleIndexWriter(HtmlConfiguration configuration, DocPath filename) {
        super(configuration, filename);
        modules = configuration.modules;
    }

    /**
     * Generate the module index page.
     *
     * @param configuration the current configuration of the doclet.
     * @throws DocFileIOException if there is a problem generating the module index page
     */
    public static void generate(HtmlConfiguration configuration) throws DocFileIOException {
        DocPath filename = DocPaths.INDEX;
        ModuleIndexWriter mdlgen = new ModuleIndexWriter(configuration, filename);
        mdlgen.buildOverviewIndexFile("doclet.Window_Overview_Summary", "module index");
    }

    /**
     * Adds the list of modules.
     *
     * @param main the documentation tree to which the modules list will be added
     */
    @Override
    protected void addIndex(Content main) {
        Map<String, SortedSet<ModuleElement>> groupModuleMap
                = configuration.group.groupModules(modules);

        if (!groupModuleMap.keySet().isEmpty()) {
            TableHeader tableHeader = new TableHeader(contents.moduleLabel, contents.descriptionLabel);
            Table table =  new Table(HtmlStyle.overviewSummary, HtmlStyle.summaryTable)
                    .setHeader(tableHeader)
                    .setColumnStyles(HtmlStyle.colFirst, HtmlStyle.colLast)
                    .setId("all-modules-table")
                    .setDefaultTab(resources.getText("doclet.All_Modules"))
                    .setTabScript(i -> "show(" + i + ");")
                    .setTabId(i -> (i == 0) ? "t0" : ("t" + (1 << (i - 1))));

            // add the tabs in command-line order
            for (String groupName : configuration.group.getGroupList()) {
                Set<ModuleElement> groupModules = groupModuleMap.get(groupName);
                if (groupModules != null) {
                    table.addTab(groupName, groupModules::contains);
                }
            }

            for (ModuleElement mdle : modules) {
                if (!mdle.isUnnamed()) {
                    if (!(options.noDeprecated() && utils.isDeprecated(mdle))) {
                        Content moduleLinkContent = getModuleLink(mdle, new StringContent(mdle.getQualifiedName().toString()));
                        Content summaryContent = new ContentBuilder();
                        addSummaryComment(mdle, summaryContent);
                        table.addRow(mdle, moduleLinkContent, summaryContent);
                    }
                }
            }

            main.add(table);

            if (table.needsScript()) {
                mainBodyScript.append(table.getScript());
            }
        }
    }
}

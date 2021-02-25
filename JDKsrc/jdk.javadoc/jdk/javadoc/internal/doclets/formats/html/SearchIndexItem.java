/*
 * Copyright (c) 2015, 2020, Oracle and/or its affiliates. All rights reserved.
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

import javax.lang.model.element.Element;

/**
 * Index item for search.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class SearchIndexItem {

    enum Category {
        MODULES,
        PACKAGES,
        TYPES,
        MEMBERS,
        /**
         * The category of items corresponding to {@code {@index}} tags.
         */
        INDEX,
        /**
         * The category of items corresponding to {@code {@systemProperty}} tags.
         */
        SYSTEM_PROPERTY
    }

    private Category category;
    private String label = "";
    private String url = "";
    private String containingModule = "";
    private String containingPackage = "";
    private String containingClass = "";
    private String holder = "";
    private String description = "";
    private Element element;

    public void setLabel(String l) {
        label = l;
    }

    public String getLabel() {
        return label;
    }

    public void setUrl(String u) {
        url = u;
    }

    public String getUrl() {
        return url;
    }

    public void setContainingModule(String m) {
        containingModule = m;
    }

    public void setContainingPackage(String p) {
        containingPackage = p;
    }

    public void setContainingClass(String c) {
        containingClass = c;
    }

    public void setCategory(Category c) {
        category = c;
    }

    public void setHolder(String h) {
        holder = h;
    }

    public String getHolder() {
        return holder;
    }

    public void setDescription(String d) {
        description = d;
    }

    public String getDescription() {
        return description;
    }

    protected Category getCategory() {
        return category;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        // TODO: Additional processing is required, see JDK-8238495
        StringBuilder item = new StringBuilder();
        switch (category) {
            case MODULES:
                item.append("{")
                        .append("\"l\":\"").append(label).append("\"")
                        .append("}");
                break;
            case PACKAGES:
                item.append("{");
                if (!containingModule.isEmpty()) {
                    item.append("\"m\":\"").append(containingModule).append("\",");
                }
                item.append("\"l\":\"").append(label).append("\"");
                if (!url.isEmpty()) {
                    item.append(",\"u\":\"").append(url).append("\"");
                }
                item.append("}");
                break;
            case TYPES:
                item.append("{");
                if (!containingPackage.isEmpty()) {
                    item.append("\"p\":\"").append(containingPackage).append("\",");
                }
                if (!containingModule.isEmpty()) {
                    item.append("\"m\":\"").append(containingModule).append("\",");
                }
                item.append("\"l\":\"").append(label).append("\"");
                if (!url.isEmpty()) {
                    item.append(",\"u\":\"").append(url).append("\"");
                }
                item.append("}");
                break;
            case MEMBERS:
                item.append("{");
                if (!containingModule.isEmpty()) {
                    item.append("\"m\":\"").append(containingModule).append("\",");
                }
                item.append("\"p\":\"").append(containingPackage).append("\",")
                        .append("\"c\":\"").append(containingClass).append("\",")
                        .append("\"l\":\"").append(label).append("\"");
                if (!url.isEmpty()) {
                    item.append(",\"u\":\"").append(url).append("\"");
                }
                item.append("}");
                break;
            case INDEX:
            case SYSTEM_PROPERTY:
                item.append("{")
                        .append("\"l\":\"").append(label).append("\",")
                        .append("\"h\":\"").append(holder).append("\",");
                if (!description.isEmpty()) {
                    item.append("\"d\":\"").append(description).append("\",");
                }
                item.append("\"u\":\"").append(url).append("\"")
                        .append("}");
                break;
            default:
                throw new AssertionError("Unexpected category: " + category);
        }
        return item.toString();
    }

    /**
     * Get the part of the label after the last dot, or whole label if no dots.
     *
     * @return the simple name
     */
    public String getSimpleName() {
        return label.substring(label.lastIndexOf('.') + 1);
    }
}

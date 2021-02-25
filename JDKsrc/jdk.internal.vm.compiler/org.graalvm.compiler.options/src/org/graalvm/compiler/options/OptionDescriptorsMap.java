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
 */


package org.graalvm.compiler.options;

import java.util.Iterator;

import jdk.internal.vm.compiler.collections.EconomicMap;

/**
 * A {@link OptionDescriptor} implementation that wraps an existing map from {@link String}s to
 * {@link OptionDescriptor}s.
 */
public final class OptionDescriptorsMap implements OptionDescriptors {
    private final EconomicMap<String, OptionDescriptor> map;

    public OptionDescriptorsMap(EconomicMap<String, OptionDescriptor> map) {
        this.map = map;
    }

    @Override
    public OptionDescriptor get(String key) {
        return map.get(key);
    }

    @Override
    public Iterator<OptionDescriptor> iterator() {
        return map.getValues().iterator();
    }
}

/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
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


package org.graalvm.graphio;

final class DefaultGraphTypes implements GraphTypes {
    static final GraphTypes DEFAULT = new DefaultGraphTypes();

    private DefaultGraphTypes() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?> enumClass(Object enumValue) {
        if (enumValue instanceof Enum<?>) {
            // check that the enum class is not actually an anonymous subclass:
            Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) enumValue.getClass();
            Enum<?>[] constants = enumClass.getEnumConstants();
            if (constants == null && enumClass.isAnonymousClass()) {
                enumClass = (Class<? extends Enum<?>>) enumClass.getSuperclass();
            }
            return enumClass;
        }
        return null;
    }

    @Override
    public int enumOrdinal(Object obj) {
        if (obj instanceof Enum<?>) {
            return ((Enum<?>) obj).ordinal();
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String[] enumTypeValues(Object clazz) {
        if (clazz instanceof Class<?>) {
            Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) clazz;
            Enum<?>[] constants = enumClass.getEnumConstants();
            if (constants != null) {
                String[] names = new String[constants.length];
                for (int i = 0; i < constants.length; i++) {
                    names[i] = constants[i].name();
                }
                return names;
            }
        }
        return null;
    }

    @Override
    public String typeName(Object clazz) {
        if (clazz instanceof Class<?>) {
            return ((Class<?>) clazz).getName();
        }
        return null;
    }

}

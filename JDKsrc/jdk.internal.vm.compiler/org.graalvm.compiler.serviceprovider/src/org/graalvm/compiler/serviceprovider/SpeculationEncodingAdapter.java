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
 */


package org.graalvm.compiler.serviceprovider;

import java.util.ArrayList;

import jdk.vm.ci.code.BytecodePosition;

class SpeculationEncodingAdapter implements SpeculationReasonGroup.SpeculationContextObject.Visitor {
    private ArrayList<Object> objects;

    SpeculationEncodingAdapter() {
        this.objects = null;
    }

    Object[] flatten(Object[] context) {
        boolean flatten = false;
        for (Object c : context) {
            if (c instanceof SpeculationReasonGroup.SpeculationContextObject || (c != null && c.getClass() == BytecodePosition.class)) {
                // Needs extra work to flatten the representation
                flatten = true;
                break;
            }
        }
        if (!flatten) {
            return context;
        }
        objects = new ArrayList<>();
        for (Object c : context) {
            if (c instanceof SpeculationReasonGroup.SpeculationContextObject) {
                SpeculationReasonGroup.SpeculationContextObject sco = (SpeculationReasonGroup.SpeculationContextObject) c;
                // These are compiler objects which all have the same class
                // loader so the class name uniquely identifies the class.
                objects.add(c.getClass().getName());
                sco.accept(this);
            } else if (c != null && c.getClass() == BytecodePosition.class) {
                BytecodePosition p = (BytecodePosition) c;
                objects.add(c.getClass().getName());
                while (p != null) {
                    visitInt(p.getBCI());
                    objects.add(p.getMethod());
                    p = p.getCaller();
                }
            } else {
                objects.add(c);
            }
        }
        return objects.toArray();
    }

    @Override
    public void visitBoolean(boolean v) {
        objects.add((byte) (v ? 1 : 0));
    }

    @Override
    public void visitByte(byte v) {
        objects.add(v);
    }

    @Override
    public void visitChar(char v) {
        objects.add(v);
    }

    @Override
    public void visitShort(short v) {
        objects.add(v);
    }

    @Override
    public void visitInt(int v) {
        objects.add(v);
    }

    @Override
    public void visitLong(long v) {
        objects.add(v);
    }

    @Override
    public void visitFloat(float v) {
        objects.add(Float.floatToRawIntBits(v));
    }

    @Override
    public void visitDouble(double v) {
        objects.add(Double.doubleToRawLongBits(v));
    }

    @Override
    public void visitObject(Object v) {
        if (v == null) {
            objects.add(0);
        } else {
            objects.add(v);
        }
    }
}

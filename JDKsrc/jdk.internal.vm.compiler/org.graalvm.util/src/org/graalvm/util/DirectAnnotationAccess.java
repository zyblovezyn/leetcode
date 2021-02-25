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


package org.graalvm.util;

//Checkstyle: allow reflection
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Wrapper class for annotation access. The purpose of this class is to encapsulate the
 * AnnotatedElement.getAnnotation() to avoid the use of the "Checkstyle: allow direct annotation
 * access " and "Checkstyle: disallow direct annotation access" comments for situations where the
 * annotation access doesn't need to guarded, i.e., in runtime code or code that accesses annotation
 * on non-user types. See {@link GuardedAnnotationAccess} for details on these checkstyle rules.
 */
public class DirectAnnotationAccess {

    public static <T extends Annotation> boolean isAnnotationPresent(AnnotatedElement element, Class<T> annotationClass) {
        return element.getAnnotation(annotationClass) != null;
    }

    public static <T extends Annotation> T getAnnotation(AnnotatedElement element, Class<T> annotationType) {
        return element.getAnnotation(annotationType);
    }
}

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

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * Wrapper class for annotation access that defends against
 * https://bugs.openjdk.java.net/browse/JDK-7183985: when an annotation declares a Class<?> array
 * parameter and one of the referenced classes is not present on the classpath parsing the
 * annotations will result in an ArrayStoreException instead of caching of a
 * TypeNotPresentExceptionProxy. This is a problem in JDK8 but was fixed in JDK11+. This wrapper
 * class also defends against incomplete class path issues. If the element for which annotations are
 * queried is a JMVCI value, i.e., a HotSpotResolvedJavaField, or HotSpotResolvedJavaMethod, the
 * annotations are read via HotSpotJDKReflection using the
 * getFieldAnnotation()/getMethodAnnotation() methods which first construct the field/method object
 * via CompilerToVM.asReflectionField()/CompilerToVM.asReflectionExecutable() which eagerly try to
 * resolve the types referenced in the element signature. If a field declared type or a method
 * return type is missing then JVMCI throws a NoClassDefFoundError.
 */
public final class GuardedAnnotationAccess {

    public static boolean isAnnotationPresent(AnnotatedElement element, Class<? extends Annotation> annotationClass) {
        return getAnnotation(element, annotationClass) != null;
    }

    public static <T extends Annotation> T getAnnotation(AnnotatedElement element, Class<T> annotationType) {
        try {
            return element.getAnnotation(annotationType);
        } catch (ArrayStoreException | NoClassDefFoundError e) {
            /*
             * Returning null essentially means that the element doesn't declare the annotationType,
             * but we cannot know that since the annotation parsing failed. However, this allows us
             * to defend against crashing the image builder if the above JDK bug is encountered in
             * user code or if the user code references types missing from the classpath.
             */
            return null;
        }
    }

    public static Annotation[] getAnnotations(AnnotatedElement element) {
        try {
            return element.getAnnotations();
        } catch (ArrayStoreException | NoClassDefFoundError e) {
            /*
             * Returning an empty array essentially means that the element doesn't declare any
             * annotations, but we know that it is not true since the reason the annotation parsing
             * failed is because some annotation referenced a missing class. However, this allows us
             * to defend against crashing the image builder if the above JDK bug is encountered in
             * user code or if the user code references types missing from the classpath.
             */
            return new Annotation[0];
        }
    }

    public static <T extends Annotation> T getDeclaredAnnotation(AnnotatedElement element, Class<T> annotationType) {
        try {
            return element.getDeclaredAnnotation(annotationType);
        } catch (ArrayStoreException | NoClassDefFoundError e) {
            /*
             * Returning null essentially means that the element doesn't declare the annotationType,
             * but we cannot know that since the annotation parsing failed. However, this allows us
             * to defend against crashing the image builder if the above JDK bug is encountered in
             * user code or if the user code references types missing from the classpath.
             */
            return null;
        }
    }

    public static Annotation[] getDeclaredAnnotations(AnnotatedElement element) {
        try {
            return element.getDeclaredAnnotations();
        } catch (ArrayStoreException | NoClassDefFoundError e) {
            /*
             * Returning an empty array essentially means that the element doesn't declare any
             * annotations, but we know that it is not true since the reason the annotation parsing
             * failed is because it at least one annotation referenced a missing class. However,
             * this allows us to defend against crashing the image builder if the above JDK bug is
             * encountered in user code or if the user code references types missing from the
             * classpath.
             */
            return new Annotation[0];
        }
    }
}

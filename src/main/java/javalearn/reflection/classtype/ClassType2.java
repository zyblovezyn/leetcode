package javalearn.reflection.classtype;

import sun.awt.SunHints;

public class ClassType2 {
    public static void main(String[] args) {
        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);
    }

    static void printClassInfo(Class cls) {
        System.out.println("Class Name: " + cls.getName());
        System.out.println("Simple Name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package Name: " + cls.getPackage());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is arrary: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }
}

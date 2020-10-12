package javalearn.reflection.field;

import java.lang.reflect.Field;

public class test2 {
    public static void main(String[] args) {
        Field f = null;
        try {
            f = String.class.getDeclaredField("value");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (f == null) {
            return;
        }
        System.out.println(f.getName());
        System.out.println(f.getType());

    }
}

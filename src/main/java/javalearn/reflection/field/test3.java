package javalearn.reflection.field;

import java.lang.reflect.Field;

public class test3 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Object p = new Person3("Xiao Ming");
        Class cls = p.getClass();
        Field f = cls.getDeclaredField("name");
        f.setAccessible(true);
        Object value = f.get(p);
        System.out.println(value);
    }
}

class Person3 {
    private String name;

    public Person3(String name) {
        this.name = name;
    }
}

package javalearn.reflection.classtype;

public class ClassType3 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class cls = String.class;
        String s = (String) cls.newInstance();
    }
}

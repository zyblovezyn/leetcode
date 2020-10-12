package javalearn.reflection.classtype;

public class ClassType1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class cls = String.class;

        String s = "hello";
        Class cls2 = s.getClass();

        Class cls3 = Class.forName("java.lang.String");

        boolean sameClass = cls == cls2;
        System.out.println(sameClass);

        test1();
    }

    public static void test1() {
        Integer n = new Integer(123);
        boolean b1 = n instanceof Integer; // true，因为n是Integer类型
        boolean b2 = n instanceof Number; // true，因为n是Number类型的子类

        boolean b3 = (n.getClass() == Integer.class); // true，因为n.getClass()返回Integer.class
       // boolean b4 = (n.getClass() == Number.class); // false，因为Integer.class!=Number.class

        System.out.println("b1 = "+b1);
        System.out.println("b2 = "+b2);
        System.out.println("b3 = "+b3);
        //System.out.println("b4 = "+b4);
    }
}

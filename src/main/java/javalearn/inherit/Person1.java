package javalearn.inherit;

public class Person1 {
    protected String name;

    public Person1(String name) {
        System.out.println("Person Contructor...." + name);
    }
}

class Husband1 extends Person1 {
    public Husband1() {
        /*

         * 必须显式的调用父类的构造函数，否则会报错，因为父类里面我们
         * 创建了一个自定义带参数构造器，而没有重载一个无参构造器
         * 系统就不会为我们创建默认构造器，在这里也就不会隐式地调用父类的默认构造器
         *
         * */
        super("chensy");
        System.out.println("Husband Constructor...");
    }

    public static void main(String[] args) {
        Husband1 husband1 = new Husband1();
    }
}

package javalearn.inherit;

public class Person {
    protected String name;

    public Person(String name) {
        System.out.println("Person Constructor" + name);
    }

    public Person() {
        System.out.println("Person Constructor");
    }
}

class Husband extends Person {
    public Husband() {
        //从结果可以看出，系统自动调用父类的Person()构造函数
        System.out.println("Husband Constructor");
    }

    public static void main(String[] args) {
        Husband husband = new Husband();
    }
}

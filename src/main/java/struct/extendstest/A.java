package struct.extendstest;

public class A {
    public A() {
        System.out.println("A.A");
    }
}

class B extends A {
    public B() {
        System.out.println("B.B");
    }
}

class C extends B {
    public C() {
        System.out.println("C.C");
    }
}

class test {
    public static void main(String[] args) {
        C c = new C();
    }
}


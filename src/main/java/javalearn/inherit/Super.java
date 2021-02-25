package javalearn.inherit;

public class Super {
    public void print() {
        System.out.println("Super.print");
    }
}

class Test extends Super {
    public void print() {
        System.out.println("Test.print");
    }

    public static void main(String[] args) {
        Super t = new Super();
        t.print();
    }
}

package designzen.doorface.better;

public class Facade {
    private ClassA a = new ClassA();
    private ClassB b = new ClassB();
    private Context c = new Context();

    public void methodA() {
        a.doSomething();
    }

    public void methodB() {
        b.doSomething();
    }

    public void methodC() {
        this.c.complexMethod();
    }
}

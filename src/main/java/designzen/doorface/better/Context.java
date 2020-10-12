package designzen.doorface.better;

public class Context {
    private ClassA a=new ClassA();
    private ClassC c=new ClassC();

    public void complexMethod() {
        this.a.doSomething();
        this.c.doSomething();
    }
}

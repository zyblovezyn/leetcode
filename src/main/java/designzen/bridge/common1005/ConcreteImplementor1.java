package designzen.bridge.common1005;

public class ConcreteImplementor1 implements Implementor {
    public ConcreteImplementor1() {
    }

    @Override
    public void doSomething() {
        System.out.println("ConcreteImplementor1.doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteImplementor1.doAnything");
    }
}

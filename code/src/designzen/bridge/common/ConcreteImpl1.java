package designzen.bridge.common;

public class ConcreteImpl1 implements Implementor {

    @Override
    public void doSomething() {
        System.out.println("ConcreteImpl1.doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteImpl1.doAnything");
    }
}

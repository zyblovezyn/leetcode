package designzen.bride.common;

public class ConcreteImpl2 implements Implementor {
    @Override
    public void doSomething() {
        System.out.println("ConcreteImpl2.doSOmething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteImpl2.doAnything");
    }
}

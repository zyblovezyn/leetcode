package designzen.bride.common0721;

public class ConcreteImpl1 implements Implementor {
    @Override
    public void doSomething() {
        System.out.println("ConcretImpl1.doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcretImpl1.doAnything");
    }
}

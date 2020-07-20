package designzen.bride.common0721;

public class ConcreteImpl2 implements Implementor {
    @Override
    public void doSomething() {
        System.out.println("ComcreteImpl2.doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ComcreteImpl2.doAnything");
    }
}

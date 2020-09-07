package designzen.strategy.common;

public class ConcreteStrategy1 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("ConcreteStrategy1.doSomething");
    }
}

package designzen.observer;

public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        System.out.println("ConcreteObserver.update");
    }
}

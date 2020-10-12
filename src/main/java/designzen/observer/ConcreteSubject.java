package designzen.observer;

public class ConcreteSubject extends Subject {
    void doSomething() {
        super.notifyObservers();
    }
}

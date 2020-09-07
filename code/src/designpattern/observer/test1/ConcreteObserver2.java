package designpattern.observer.test1;

public class ConcreteObserver2 implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者2做出反应！");
    }
}

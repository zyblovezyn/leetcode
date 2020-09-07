package designpattern.observer.test1;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    //增加观察者
    public void add(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public abstract void notifyObserver();//通知观察者
}

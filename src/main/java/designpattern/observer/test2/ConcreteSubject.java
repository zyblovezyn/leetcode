package designpattern.observer.test2;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> observerList=new ArrayList<>();
    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        if (!observerList.isEmpty()) {
            observerList.remove(observer);
        }
    }

    @Override
    public void notify(String string) {
        for (int i = 0; i < observerList.size(); i++) {
            observerList.get(i).update(string);
        }
    }
}

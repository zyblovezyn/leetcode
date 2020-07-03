package designpattern.observer.test1;

public class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生变化!");
        System.out.println("-----------");
        for (Observer obs : observers) {
            obs.response();
        }
    }
}

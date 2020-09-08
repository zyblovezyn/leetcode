package designpattern.observer.test2;

public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notify(String string);
}
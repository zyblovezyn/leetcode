package designzen.adapter.common;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget.request");
    }
}

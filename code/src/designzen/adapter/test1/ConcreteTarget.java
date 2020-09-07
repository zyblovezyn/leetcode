package designzen.adapter.test1;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget.request");
    }
}

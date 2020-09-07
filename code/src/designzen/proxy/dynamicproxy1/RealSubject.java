package designzen.proxy.dynamicproxy1;

public class RealSubject implements Subject {
    @Override
    public void doSomething(String string) {
        System.out.println("RealSubject.doSomething");
    }
}

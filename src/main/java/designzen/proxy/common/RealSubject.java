package designzen.proxy.common;

public class RealSubject implements Subject {
    @Override
    public void request() {
        //业务逻辑处理
        System.out.println("RealSubject.request");
    }
}

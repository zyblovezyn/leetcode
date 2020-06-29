package designzen.proxy.dynamicproxy1;

import java.lang.reflect.InvocationHandler;

public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        InvocationHandler handler = new MyInvocationHandler(subject);
        Subject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), handler);
        proxy.doSomething("Finish!");


        Subject proxy1 = SubjectDynamicProxy.newProxyInstance(subject);
        proxy1.doSomething("Finish");
    }
}

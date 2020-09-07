package designzen.proxy.dynamicproxy1;

import java.lang.reflect.InvocationHandler;

public class SubjectDynamicProxy extends DynamicProxy {
    public static <T> T newProxyInstance(Subject subject) {
        ClassLoader loader = subject.getClass().getClassLoader();
        Class<?>[] classes = subject.getClass().getInterfaces();
        InvocationHandler handler = new MyInvocationHandler(subject);
        return newProxyInstance(loader, classes, handler);
    }
}

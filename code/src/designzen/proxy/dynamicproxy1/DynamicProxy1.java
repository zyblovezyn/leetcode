package designzen.proxy.dynamicproxy1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy1<T> {
    public static <T> T newInstanceProxy(ClassLoader loader, Class<?>[] classes, InvocationHandler handler) {
        return (T)Proxy.newProxyInstance(loader,classes,handler);
    }
}

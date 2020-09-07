package designzen.proxy.dynamicproxy1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler1 implements InvocationHandler {
    private Object obj;

    public MyInvocationHandler1(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = method.invoke(this.obj, args);
        return res;
    }
}

package designzen.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GamePlayIH implements InvocationHandler {

    // 被代理者
    Class cls = null;
    //被代理实例
    Object obj = null;

    public GamePlayIH(Object obj) {
        this.obj = obj;
    }

    //调用被代理的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.obj, args);
        if (method.getName().equalsIgnoreCase("logon")) {
            System.out.println("有人在盗用我的账号！");
        }
        return result;
    }
}

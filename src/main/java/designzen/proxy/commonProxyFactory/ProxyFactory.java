package designzen.proxy.commonProxyFactory;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ?建??代理?象
 * ??代理不需要??接口,但是需要指定接口?型
 */
public class ProxyFactory {
    //??一个目??象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("start commit!");
                        //?行目??象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("commit");
                        return returnValue;
                    }
                }
        );
    }
}

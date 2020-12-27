package designzen.proxy.commonProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ?��??�㗝?��
 * ??�㗝�s���v??�ڌ�,�A�����v�w��ڌ�?�^
 */
public class ProxyFactory {
    //??�꘢��??��
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
                        //?�s��??�ە��@
                        Object returnValue = method.invoke(target, args);
                        System.out.println("commit");
                        return returnValue;
                    }
                }
        );
    }
}

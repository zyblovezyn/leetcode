package designzen.proxy.commoncglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglibq?γHΚ
 * ?UserDaoέΰΆ???κ’q??Ϋ
 */
public class ProxyFactory implements MethodInterceptor {
    //??κ’Ϊ??Ϋ
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //?Ϊ??Ϋ?κ’γ?Ϋ
    public Object getProxyInstance() {
        //1 Hο?
        Enhancer enhancer=new Enhancer();
        //2 ?u?
        enhancer.setSuperclass(target.getClass());
        //3 ?uρ?
        enhancer.setCallback(this);
        // 4 ?q?(γ?Ϋ)
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start transaction");
        Object returnValue = method.invoke(target, objects);
        System.out.println("transacting");
        return returnValue;
    }
}

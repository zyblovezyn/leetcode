package designzen.proxy.commoncglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib�q?�㗝�H��
 * ?UserDao�ݓ�����???���꘢�q??��
 */
public class ProxyFactory implements MethodInterceptor {
    //??�꘢��??��
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //?��??��?���꘢�㗝?��
    public Object getProxyInstance() {
        //1 �H��?
        Enhancer enhancer=new Enhancer();
        //2 ?�u��?
        enhancer.setSuperclass(target.getClass());
        //3 ?�u��?����
        enhancer.setCallback(this);
        // 4 ?���q?(�㗝?��)
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

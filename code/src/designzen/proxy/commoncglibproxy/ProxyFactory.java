package designzen.proxy.commoncglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib子?代理工厂
 * ?UserDao在内存中???建一个子??象
 */
public class ProxyFactory implements MethodInterceptor {
    //??一个目??象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //?目??象?建一个代理?象
    public Object getProxyInstance() {
        //1 工具?
        Enhancer enhancer=new Enhancer();
        //2 ?置父?
        enhancer.setSuperclass(target.getClass());
        //3 ?置回?函数
        enhancer.setCallback(this);
        // 4 ?建子?(代理?象)
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

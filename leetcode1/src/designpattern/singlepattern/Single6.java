package designpattern.singlepattern;

import org.omg.CORBA.PRIVATE_MEMBER;

//静态内部类实现单例模式  推荐使用
public class Single6 {
    private Single6() {
    }

    private static class SingleInstance {
        private static final Single6 INSTANCE = new Single6();
    }

    public static Single6 getInstance() {
        return SingleInstance.INSTANCE;
    }
}

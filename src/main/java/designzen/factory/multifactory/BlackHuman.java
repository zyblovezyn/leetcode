package designzen.factory.multifactory;

import designzen.factory.test2.Human;

public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黑人的肤色是黑色的");
    }

    @Override
    public void talk() {
        System.out.println("黑人会说话 一般人听不懂");
    }
}

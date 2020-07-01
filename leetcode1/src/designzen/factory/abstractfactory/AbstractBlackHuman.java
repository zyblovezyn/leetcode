package designzen.factory.abstractfactory;

public abstract class AbstractBlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黑人的肤色是黑色的");
    }

    @Override
    public void talk() {
        System.out.println("黑人会说话 一般人听不懂");
    }
}

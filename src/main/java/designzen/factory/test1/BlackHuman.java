package designzen.factory.test1;

public class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是黑色人种");
    }

    @Override
    public void talk() {
        System.out.println("我说的你们听不懂！");
    }
}

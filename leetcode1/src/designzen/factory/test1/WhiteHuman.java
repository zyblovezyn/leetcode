package designzen.factory.test1;

public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是白色皮肤的人！");
    }

    @Override
    public void talk() {
        System.out.println("我说的是英语！");
    }
}

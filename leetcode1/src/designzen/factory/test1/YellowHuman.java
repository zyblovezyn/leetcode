package designzen.factory.test1;

public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("我是换色皮肤的人！");
    }

    @Override
    public void talk() {
        System.out.println("我说的是汉语！");
    }
}

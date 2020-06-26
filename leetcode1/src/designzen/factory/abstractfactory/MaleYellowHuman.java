package designzen.factory.abstractfactory;

public class MaleYellowHuman extends AbstractYellowHuman {
    @Override
    public void getSex() {
        System.out.println("我是黄人男性");
    }
}

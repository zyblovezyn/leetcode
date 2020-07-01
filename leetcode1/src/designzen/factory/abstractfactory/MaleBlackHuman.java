package designzen.factory.abstractfactory;

public class MaleBlackHuman extends AbstractBlackHuman {
    @Override
    public void getSex() {
        System.out.println("我是黑人男性");
    }
}

package designzen.factory.abstractfactory;

public class FemaleBlackHuman extends AbstractBlackHuman {
    @Override
    public void getSex() {
        System.out.println("我是黑人女性");
    }
}

package designzen.factory.abstractfactory;

public class FemaleWhiteHuman extends AbstractWhiteHuman {
    @Override
    public void getSex() {
        System.out.println("我是白人女性");
    }
}

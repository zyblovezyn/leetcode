package designzen.abstractfactory.abstractfactory;

public class MaleWhiteHuman extends AbstractWhiteHuman {
    @Override
    public void getSex() {
        System.out.println("我是白人男性");
    }
}

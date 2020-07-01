package designzen.factory.multifactory;

public class YellowFactory extends AbstractFactory {
    @Override
    public Human createHuman() {
        return (Human) new YellowHuman();
    }
}

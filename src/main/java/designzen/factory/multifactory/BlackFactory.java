package designzen.factory.multifactory;

public class BlackFactory extends AbstractFactory {
    @Override
    public Human createHuman() {
        return (Human) new BlackHuman();
    }
}

package designzen.factory.multifactory;

public class WhiteFactory extends AbstractFactory {
    @Override
    public Human createHuman() {
        return (Human) new WhiteHuman();
    }
}

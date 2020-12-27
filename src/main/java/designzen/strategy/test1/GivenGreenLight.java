package designzen.strategy.test1;

public class GivenGreenLight implements IStartegy {
    @Override
    public void operate() {
        System.out.println("GivenGreenLight.operate");
    }
}

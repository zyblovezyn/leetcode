package designzen.strategy.test1;

public class BackDoor implements IStartegy {
    @Override
    public void operate() {
        System.out.println("BackDoor.operate");
    }
}

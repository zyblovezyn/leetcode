package designzen.strategy.test1;

public class Client {
    public static void main(String[] args) {
        ContextTest contextTest;
        contextTest = new ContextTest(new BackDoor());
        contextTest.operate();
        contextTest = new ContextTest(new GivenGreenLight());
        contextTest.operate();
        contextTest = new ContextTest(new BlockEnemy());
        contextTest.operate();
    }
}

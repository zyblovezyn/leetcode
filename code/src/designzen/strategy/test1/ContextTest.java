package designzen.strategy.test1;

public class ContextTest {
    private IStartegy startegy;

    public ContextTest(IStartegy startegy) {
        this.startegy = startegy;
    }

    public void operate() {
        this.startegy.operate();
    }
}

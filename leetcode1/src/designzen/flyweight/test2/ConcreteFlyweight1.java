package designzen.flyweight.test2;

public class ConcreteFlyweight1 extends Flyweight {
    public ConcreteFlyweight1(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate() {
        System.out.println("ConcreteFlyweight1.operate");
    }
}

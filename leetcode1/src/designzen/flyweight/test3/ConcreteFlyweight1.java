package designzen.flyweight.test3;

public class ConcreteFlyweight1 extends Flyweight {
    public ConcreteFlyweight1(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate() {
        System.out.println("ConcreteFlyweight1.operate");
    }
}

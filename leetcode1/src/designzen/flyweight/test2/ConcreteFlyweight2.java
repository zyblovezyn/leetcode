package designzen.flyweight.test2;

public class ConcreteFlyweight2 extends Flyweight {
    public ConcreteFlyweight2(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate() {
        System.out.println("ConcreteFlyweight2.operate");
    }
}

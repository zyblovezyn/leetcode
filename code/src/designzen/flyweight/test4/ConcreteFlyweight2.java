package designzen.flyweight.test4;

public class ConcreteFlyweight2 extends Flyweight {

    @Override
    public void operate() {
        System.out.println("ConcreteFlyweight2.operate");
    }

    public ConcreteFlyweight2(String outSide) {
        super(outSide);
    }
}

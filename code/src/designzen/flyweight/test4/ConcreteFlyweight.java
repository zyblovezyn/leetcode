package designzen.flyweight.test4;

public class ConcreteFlyweight extends Flyweight {
    public ConcreteFlyweight(String outSide) {
        super(outSide);
    }

    @Override
    public void operate() {
        System.out.println("ConcreteFlyweight.operate");
    }
}

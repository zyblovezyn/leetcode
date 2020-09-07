package designzen.bride.test1;

public class IPod extends Product {
    @Override
    public void beProduct() {
        System.out.println("IPod.beProduct");
    }

    @Override
    public void beSelled() {
        System.out.println("IPod.beSelled");
    }
}

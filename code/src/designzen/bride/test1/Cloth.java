package designzen.bride.test1;

public class Cloth extends Product {
    @Override
    public void beProduct() {
        System.out.println("Cloth.beProduct");
    }

    @Override
    public void beSelled() {
        System.out.println("Cloth.beSelled");
    }
}

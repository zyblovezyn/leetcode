package designzen.bride.test1;

public class House extends Product {
    @Override
    public void beProduct() {
        System.out.println("House.beProduct");
    }

    @Override
    public void beSelled() {
        System.out.println("House.beSelled");
    }
}

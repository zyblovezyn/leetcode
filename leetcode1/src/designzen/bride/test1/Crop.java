package designzen.bride.test1;

public abstract class Crop {
    private Product product;

    public Crop(Product product) {
        this.product = product;
    }

    public void makeMoney() {
        this.product.beProduct();
        this.product.beSelled();
    }
}

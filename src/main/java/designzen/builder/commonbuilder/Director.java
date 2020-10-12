package designzen.builder.commonbuilder;

public class Director {
    private Builder builder = new ConcreteProduct();

    public Product getProduct() {
        builder.setPart();
        /*
         * 设置不同的零件，产生不同的产品
         * */
        return builder.builderProduct();
    }
}

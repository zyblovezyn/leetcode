package designzen.abstractfactory.commonfactory02;

import designzen.builder.commonbuilder.ConcreteProduct;

public class client {
    public static void main(String[] args) {
        Creator creator = new CreateFactory();
        Product product = creator.createProduct(ConcreteProduct1.class);
        product.method2();

        product = creator.createProduct(ConcreteProduct2.class);
        product.method2();

        product = creator.createProduct(ConcreteProduct3.class);
        product.method2();
    }
}

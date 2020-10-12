package designzen.abstractfactory.commonfactorytest;

public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        product.method1();
        product.method2();
    }
}

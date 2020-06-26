package designzen.factory.CommonFactory;

public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        product.method1();
        product.method2();

        Product product3 = creator.createProduct(ConcreteProduct3.class);
        product3.method1();
        product3.method2();
    }
}

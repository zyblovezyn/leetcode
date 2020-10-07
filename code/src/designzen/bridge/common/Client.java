package designzen.bridge.common;

public class Client {
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImpl1();
        Abstraction abs = new RefineAbstraction(implementor);
        abs.request();
    }
}

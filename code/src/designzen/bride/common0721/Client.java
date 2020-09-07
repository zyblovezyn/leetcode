package designzen.bride.common0721;

public class Client {
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImpl1();
        Abstraction abstraction = new RedefineAbstraction(implementor);
        abstraction.request();
    }
}

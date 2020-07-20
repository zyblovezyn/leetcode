package designzen.bride.common0720;

public class Client {
    public static void main(String[] args) {
        Implemetor implemetor = new ConcreteImpl1();
        Abstraction abstraction = new RedefineAbstraction(implemetor);
        abstraction.request();
    }
}

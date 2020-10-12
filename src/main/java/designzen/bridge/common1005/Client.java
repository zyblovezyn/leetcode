package designzen.bridge.common1005;

public class Client {
    public static void main(String[] args) {
        Implementor imp = new ConcreteImplementor1();
        AbstractionClass abstractionClass = new RefineAbstraction(imp);
        abstractionClass.request();
    }
}

package designzen.bridge.common1005;

public class Client {
    public static void main(String[] args) {
        //定?一个??化角色
        Implementor imp = new ConcreteImplementor1();
        //定?一个抽象化角色
        AbstractionClass abstractionClass = new RefineAbstraction(imp);
        //?行行文
        abstractionClass.request();
    }
}

package designzen.bridge.common1005;

public class Client {
    public static void main(String[] args) {
        //��?�꘢??���p�F
        Implementor imp = new ConcreteImplementor1();
        //��?�꘢���ۉ��p�F
        AbstractionClass abstractionClass = new RefineAbstraction(imp);
        //?�s�s��
        abstractionClass.request();
    }
}

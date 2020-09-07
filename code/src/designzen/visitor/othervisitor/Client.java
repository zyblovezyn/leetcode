package designzen.visitor.othervisitor;

public class Client {
    public static void main(String[] args) {
        ObjectStructure o = new ObjectStructure();
        o.attach(new Man());
        o.attach(new Woman());

        //成功时的反映
        Success v1 = new Success();
        o.display(v1);

        //失败时的反映
        Failing v2 = new Failing();
        o.display(v2);

        //恋爱时的反映
        Amativeness v3 = new Amativeness();
        o.display(v3);
    }
}

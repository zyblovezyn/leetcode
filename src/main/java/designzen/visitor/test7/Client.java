package designzen.visitor.test7;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Client.java
 * @createTime 2021-01-12 14:39:00
 */
public class Client {
    public static void main(String[] args) {
        ObjectStruct objectStruct=new ObjectStruct();
        objectStruct.add(new NodeA());
        objectStruct.add(new NodeB());
        Visitor visitor=new VisitorA();
        objectStruct.action((visitor));
    }
}

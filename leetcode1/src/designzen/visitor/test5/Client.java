package designzen.visitor.test5;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Element element = ObjectStruct.createElement();
            element.accept(new Visitor());
        }
    }
}

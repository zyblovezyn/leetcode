package designzen.visitor.test4;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Element element = ObjectStruture.createElement();
            element.accept(new Visitor());
        }
    }
}

package designzen.decorator.decorator0721;

public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteCompoent();
        component = new ConcreteDecorator1(component);
        component = new ConcreteDecorator2(component);
        component.operator();
    }
}

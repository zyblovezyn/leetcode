package designzen.decorator.decorator0721;

public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    private void method1() {
        System.out.println("ConcreteDecorator1.method1");
    }

    @Override
    public void operator() {
        this.method1();
        super.operator();
    }
}

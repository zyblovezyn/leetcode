package designzen.decorator.decorator1012;

public class ConcreteDecorator1012 extends Decorator1012 {

    public ConcreteDecorator1012(Component1012 component1012) {
        super(component1012);
    }

    public void method1() {
        System.out.println("ConcreteDecorator1012.method1");
    }

    @Override
    void operate() {
        super.operate();
        this.method1();
    }
}

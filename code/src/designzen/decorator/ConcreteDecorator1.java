package designzen.decorator;

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    // 自定义自己的装饰方法
    private void method1() {
        System.out.println("ConcreteDecorator1.method1");
    }

    @Override
    public void operate() {
        this.method1();
        super.operate();
    }
}

package designzen.decorator.decorator0721;

public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    //定义自己的装饰方法
    private void method2() {
        System.out.println("ConcreteDecorator2.method2");
    }

    @Override
    public void operator() {
        this.method2();
        super.operator();
    }
}

package designzen.visitor.common;

public class ConcreteElement1 extends Element {
    @Override
    public void doSomething() {
        System.out.println("ConcreteElement1.doSomething");
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

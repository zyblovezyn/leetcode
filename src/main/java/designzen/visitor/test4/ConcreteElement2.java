package designzen.visitor.test4;

public class ConcreteElement2 extends Element {
    @Override
    public void doSomething() {
        System.out.println("ConcreteElement2.doSomething");
    }

    @Override
    public void accept(IVisitor iVisitor) {
        iVisitor.visit(this);
    }
}

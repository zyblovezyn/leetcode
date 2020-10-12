package designzen.visitor.test4;

public class ConcreteElement1 extends Element {
    @Override
    public void doSomething() {
        System.out.println("ConcreteElement1.doSomething");
    }

    @Override
    public void accept(IVisitor iVisitor) {
            iVisitor.visit(this);
    }
}

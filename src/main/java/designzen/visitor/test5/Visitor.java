package designzen.visitor.test5;

public class Visitor implements IVisitor {
    @Override
    public void visit(ConcreteElement1 concreteElement1) {
        concreteElement1.doSomething();
    }

    @Override
    public void visit(ConcreteElement2 concreteElement2) {
        concreteElement2.doSomething();
    }
}

package designzen.visitor.test4;

public abstract class Element {
    public  abstract void doSomething();
    public abstract void accept(IVisitor iVisitor);
}

package designzen.visitor.test5;

public abstract class Element {
    public abstract void doSomething();

    public abstract void accept(IVisitor visitor);
}

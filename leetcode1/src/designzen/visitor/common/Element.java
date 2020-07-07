package designzen.visitor.common;

public abstract class Element {
    public abstract void doSomething();

    public abstract void accept(IVisitor visitor);
}

package designzen.bridge.common1012;

public abstract class AbstractClass1012 {
    private designzen.bridge.common1012.implementor1012 implementor1012;

    public AbstractClass1012(designzen.bridge.common1012.implementor1012 implementor1012) {
        this.implementor1012 = implementor1012;
    }

    public designzen.bridge.common1012.implementor1012 getImplementor1012() {
        return implementor1012;
    }

    protected void doSomething() {
        this.implementor1012.doSomething();
    }

    protected void doAnything() {
        this.implementor1012.doAnything();
    }
}

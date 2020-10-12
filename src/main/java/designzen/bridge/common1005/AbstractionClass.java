package designzen.bridge.common1005;

public abstract class AbstractionClass {

    private Implementor implementor;

    public AbstractionClass(Implementor implementor) {
        this.implementor = implementor;
    }

    public Implementor getImplementor() {
        return implementor;
    }

    public void request() {
        this.implementor.doSomething();
    }
}

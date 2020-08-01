package designzen.bride.common0721;

public abstract class Abstraction {
    private Implementor impl;

    public Abstraction(Implementor impl) {
        this.impl = impl;
    }

    public void request() {
        this.impl.doSomething();
    }

    public Implementor getImpl() {
        return impl;
    }
}

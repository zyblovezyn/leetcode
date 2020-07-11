package designzen.bride.common;

public abstract class Abstraction {
    private Implementor imp;

    public Abstraction(Implementor imp) {
        this.imp = imp;
    }

    public void request() {
        this.imp.doSomething();
    }

    public Implementor getImp() {
        return imp;
    }
}

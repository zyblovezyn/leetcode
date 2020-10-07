package designzen.bridge.common1005;

public abstract class AbstractionClass {
    //定????化角色的引用
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

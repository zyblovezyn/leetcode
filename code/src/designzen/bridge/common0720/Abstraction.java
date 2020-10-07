package designzen.bridge.common0720;

public abstract class Abstraction {
    private Implemetor implemetor;

    public Abstraction(Implemetor implemetor) {
        this.implemetor = implemetor;
    }

    public void request() {
        this.implemetor.doSomething();
    }

    public Implemetor getImplemetor() {
        return implemetor;
    }
}

package designzen.bridge.common0720;

public class RedefineAbstraction extends Abstraction {
    public RedefineAbstraction(Implemetor implemetor) {
        super(implemetor);
    }

    @Override
    public void request() {
        super.request();
        super.getImplemetor().doAnything();
    }
}

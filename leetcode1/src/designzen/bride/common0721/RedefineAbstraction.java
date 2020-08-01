package designzen.bride.common0721;

public class RedefineAbstraction extends Abstraction {
    public RedefineAbstraction(Implementor impl) {
        super(impl);
    }

    //修正父类的实现
    @Override
    public void request() {
        super.request();
        super.getImpl().doAnything();
    }
}

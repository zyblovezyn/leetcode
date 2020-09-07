package designzen.bride.common;

public class RefineAbstraction extends Abstraction {
    public RefineAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void request() {
        /*
         *
         * 业务处理
         *
         * */
        super.request();
        super.getImp().doAnything();
    }
}

package designzen.bridge.common1005;

public class RefineAbstraction extends AbstractionClass {
    //����?������
    public RefineAbstraction(Implementor implementor) {
        super(implementor);
    }

    //�C����?�s?
    @Override
    public void request() {
        /*
            ???��
         */
        super.request();
        super.getImplementor().doAnything();
    }
}

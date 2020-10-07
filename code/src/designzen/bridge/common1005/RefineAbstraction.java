package designzen.bridge.common1005;

public class RefineAbstraction extends AbstractionClass {
    //•¢Ê?‘¢”Ÿ”
    public RefineAbstraction(Implementor implementor) {
        super(implementor);
    }

    //C³•ƒ?s?
    @Override
    public void request() {
        /*
            ???—
         */
        super.request();
        super.getImplementor().doAnything();
    }
}

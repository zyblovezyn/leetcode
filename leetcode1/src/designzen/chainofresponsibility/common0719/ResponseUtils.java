package designzen.chainofresponsibility.common0719;

public class ResponseUtils {
    public static final Response setChain() {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        Response response = handler1.handleMessage(new Request());
        return response;
    }
}

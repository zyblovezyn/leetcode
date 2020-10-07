package designzen.chainofresponsibility.common1005;

public class Client {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        Response1005 response1005 = handler1.handlerMessage(new Request1005());
    }
}

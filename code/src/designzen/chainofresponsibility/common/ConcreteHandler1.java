package designzen.chainofresponsibility.common;

public class ConcreteHandler1 extends Handler {
    //设置自己的处理级别
    @Override
    protected Integer getHandlerLevel() {
        System.out.println("ConcreteHandler1.getHandlerLevel");
        return Level.PAGE_VIEW_LEVEL;
    }

    // 定义自己的处理业务逻辑
    @Override
    protected Response echo(Request request) {
        System.out.println("ConcreteHandler1.echo");
        return null;
    }
}

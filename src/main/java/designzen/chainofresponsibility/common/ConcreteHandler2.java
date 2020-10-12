package designzen.chainofresponsibility.common;

public class ConcreteHandler2 extends Handler {
    //设置自己的处理级别
    @Override
    protected Integer getHandlerLevel() {
        System.out.println("ConcreteHandler2.getHandlerLevel");
        return Level.PAGE_EDIT_LEVEL;
    }

    // 定义自己的处理业务逻辑
    @Override
    protected Response echo(Request request) {
        System.out.println("ConcreteHandler2.echo");
        return null;
    }
}

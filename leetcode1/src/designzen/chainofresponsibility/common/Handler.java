package designzen.chainofresponsibility.common;

public abstract class Handler {
    private Handler nextHandler;

    public final Response HandleMessage(Request request) {
        Response response = null;
        if (this.getHandlerLevel().equals(request.getRequestLevel())) {
            response = this.echo(request);
        } else {
            if (this.nextHandler != null) {
                response = this.nextHandler.HandleMessage(request);
            } else {
                //没有适当的处理者 业务自己处理
            }
        }
        return response;
    }

    //设置下一个处理者是谁
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    protected abstract Integer getHandlerLevel();

    protected abstract Response echo(Request request);
}

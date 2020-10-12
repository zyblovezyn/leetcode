package designzen.chainofresponsibility.common1005;


public abstract class Handler {
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public final Response1005 handlerMessage(Request1005 request) {
        Response1005 response1005 = null;
        if (request.getRequestLevel().equals(this.getHandlerLevel())) {
            response1005 = this.echo(request);
        } else {
            if (this.nextHandler != null) {
                response1005 = this.nextHandler.handlerMessage(request);
            }
        }
        return response1005;
    }

    //?æ?—??
    protected abstract String getHandlerLevel();

    //?—?‹
    protected abstract Response1005 echo(Request1005 request);
}

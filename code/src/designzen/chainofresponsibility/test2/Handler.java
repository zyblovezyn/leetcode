package designzen.chainofresponsibility.test2;


public abstract class Handler {
    public final static int FATHER_LEVEL_REQUEST = 1;
    public static final int HUSBAND_LEVEL_REQUEST = 2;
    public static final int SON_LEVEL_REQUEST = 3;
    private int level = 0;
    private Handler nextHandler;

    public Handler(int level) {
        this.level = level;
    }

    public final void HandleMessage(IWoman woman) {
        if (woman.getType() == this.level) {
            this.response(woman);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.HandleMessage(woman);
            } else {
                System.out.println("已经没有后续处理了，不进行处理");
            }
        }
    }

    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    protected abstract void response(IWoman woman);
}

package designzen.chainofresponsibility.test10719;

import java.util.Objects;

public abstract class Handler {
    public static final int FATHER_LEVEL_REQUEST = 1;
    public static final int HUSBAND_LEVEL_REQUEST = 2;
    public static final int SON_LEVEL_REQUEST = 3;
    //能处理的级别
    private int level = 1;
    //责任传递下一个责任人是谁
    private Handler nextHandler;
    //每个类都要说明一下自己能处理的那些请求

    public Handler(int level) {
        this.level = level;
    }

    //一个女性要求逛街，你要处理这个请求
    public final void handleMessage(IWoman woman) {
        if (woman.getType() == this.level) {
            this.response(woman);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.response(woman);
            } else {
                System.out.println("---------- 没地方请示了 按不同意处理----------");
            }
        }
    }

    /*
        如果是不属于你的请求  就传递到到下一下个环节的人
     */
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void response(IWoman woman);
}

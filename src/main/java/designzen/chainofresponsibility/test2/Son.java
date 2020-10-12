package designzen.chainofresponsibility.test2;

public class Son extends Handler {
    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWoman woman) {
        System.out.println("--------母亲向儿子请示-------");
        System.out.println(woman.getRequest());
        System.out.println("儿子的答复是：同意\n");
    }
}

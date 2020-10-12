package designzen.chainofresponsibility.test2;

public class Husband extends Handler {
    public Husband() {
        super(Handler.HUSBAND_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWoman woman) {
        System.out.println("--------妻子向丈夫请示-------");
        System.out.println(woman.getRequest());
        System.out.println("丈夫的答复是：同意\n");
    }
}

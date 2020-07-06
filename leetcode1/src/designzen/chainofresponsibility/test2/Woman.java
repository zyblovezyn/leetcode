package designzen.chainofresponsibility.test2;

public class Woman implements IWoman {
    private int type = 0;
    private String request = "";

    public Woman(int type, String request) {
        this.type = type;
        this.request = request;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}

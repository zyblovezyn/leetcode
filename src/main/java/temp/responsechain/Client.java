package temp.responsechain;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Client.java
 * @createTime 2021-01-20 15:22:00
 */
public class Client {
    public static void main(String[] args) {
        ChainOne chainOne = new ChainOne();
        ChainTwo chainTwo = new ChainTwo();
        chainOne.setNextChain(chainTwo);

        Request request = new Request();

        chainOne.handler(request);
    }
}

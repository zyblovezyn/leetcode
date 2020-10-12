package designzen.chainofresponsibility.test1;

public class Son implements IHandler {
    @Override
    public void HandleMessage(IWoman woman) {
        System.out.println("Son.HandleMessage");
    }
}

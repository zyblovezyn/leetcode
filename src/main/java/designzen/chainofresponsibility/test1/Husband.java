package designzen.chainofresponsibility.test1;

public class Husband implements IHandler {
    @Override
    public void HandleMessage(IWoman woman) {
        System.out.println("Husband.HandleMessage");
    }
}

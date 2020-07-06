package designzen.chainofresponsibility.test1;

public class Father implements IHandler{
    @Override
    public void HandleMessage(IWoman woman) {
        System.out.println("Father.HandleMessage");
    }
}

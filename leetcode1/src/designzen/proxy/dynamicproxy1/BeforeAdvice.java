package designzen.proxy.dynamicproxy1;

public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("BeforeAdvice.exec");
    }
}

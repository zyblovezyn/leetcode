package struct.defaulttest;

public class DefaultImpl implements IDefault {
    @Override
    public void method() {
        System.out.println("DefaultImpl.method");
    }

    public static void main(String[] args) {
        IDefault d = new DefaultImpl();
        d.method();
        d.doSomething();
    }
}

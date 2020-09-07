package struct.defaulttest;

public interface IDefault {
    default public void method() {
        System.out.println("IDefault.method");
    }

    default public void doSomething() {
        System.out.println("IDefault.doSomething");
    }
}

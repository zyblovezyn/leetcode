package designzen.template.commontemplate;

public class ConcreteTemplate1 extends AbstarctTemplate {
    @Override
    public void doSomething() {
        System.out.println("ConcreteTemplate1.doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteTemplate1.doAnything");
    }
}

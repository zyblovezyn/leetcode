package designzen.template.commontemplate;

public class ConcreteTemplate2 extends AbstarctTemplate {
    @Override
    public void doSomething() {
        System.out.println("ConcreteTemplate2.doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteTemplate2.doAnything");
    }
}

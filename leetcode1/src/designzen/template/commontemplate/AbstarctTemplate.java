package designzen.template.commontemplate;

public abstract class AbstarctTemplate {
    public abstract void doSomething();

    public abstract void doAnything();

    public void templateMethod() {
        this.doAnything();
        this.doSomething();
    }
}

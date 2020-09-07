package designzen.proxy.common;

public class Proxy implements Subject {
    private Subject subject = null;

    public Proxy() {
        this.subject = new Proxy();
    }

    public Proxy(Object... objects) {
    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    public void before() {
        System.out.println("Proxy.before");
    }

    public void after() {
        System.out.println("Proxy.after");
    }
}

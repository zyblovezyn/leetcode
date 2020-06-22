package designpattern.prototype.test1;

public class Client {
    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public void operation(Prototype example){
        Prototype copyPrototype= (Prototype) example.clone();
    }
}

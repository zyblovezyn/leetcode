package designpattern.observer.test2;

public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String string) {
        System.out.println("string = " + string);
    }
}

package designpattern.singlepattern;

public class Single3 {
    private static Single3 instance;

    private Single3() {
    }

    public static Single3 getInstance(){
        if (instance == null) {
            instance=new Single3();
        }
        return instance;
    }
}

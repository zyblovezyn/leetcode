package designpattern.singlepattern;

public class Single4 {
    private static Single4 instance;

    private Single4() {
    }

    //synchronized 解决线程安全问题
    public static synchronized Single4 getInstance(){
        if (instance == null) {
            instance=new Single4();
        }
        return instance;
    }
}

package designpattern.singlepattern;

public class Single2 {

    private Single2() {
    }

    // 本类内部创建对象
    private static Single2 instance;
    static {
        instance=new Single2();
    }

    // 提供共有方法
    public static Single2 getInstance(){
        return instance;
    }
}

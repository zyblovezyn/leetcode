package designpattern.singlepattern;

public class Single1 {
    //构造器私有化 外部不能new
    private Single1() {
    }

    // 本类内部创建对象
    private final static Single1 instance=new Single1();

    // 提供共有方法
    public static Single1 getInstance(){
        return instance;
    }
}

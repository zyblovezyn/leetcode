package designpattern.singlepattern;

// 推荐使用 双重检查
public class Single5 {
    private static volatile Single5 instance;

    private Single5() {
    }

    public static Single5 getInstance() {
        if(instance==null){
            synchronized (Single5.class) {
                if (instance == null) {
                    instance=new Single5();
                }
            }
        }
        return instance;
    }
}

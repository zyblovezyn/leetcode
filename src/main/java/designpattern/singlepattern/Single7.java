package designpattern.singlepattern;

class Test7{
    public static void main(String[] args) {
       Single7 single7= Single7.INSTANCE;
       Single7 single77= Single7.INSTANCE;
        System.out.println(single7.hashCode());
        System.out.println(single77.hashCode());
        single7.sayOK();
    }
}

// 推荐使用
public enum Single7 {
    INSTANCE;

    public void sayOK() {
        System.out.println("OK");
    }
}

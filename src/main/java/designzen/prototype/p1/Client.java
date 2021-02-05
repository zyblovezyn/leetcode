package designzen.prototype.p1;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Client.java
 * @createTime 2021-01-15 15:37:00
 */
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        Thing thing=new Thing();
        thing.getArrayList().add(12);
        thing.setAge(15);
        Thing thing1 = thing.clone();//clone 在内存中以二进制流的形式拷贝数据  只拷贝本对象 而对象中的数组和引用对象不拷贝
        thing1.getArrayList().add(13);
        thing1.setAge(16);
        System.out.println(thing.getArrayList());
        System.out.println(thing1.getArrayList());
        System.out.println(thing.getAge());
        System.out.println(thing1.getAge());
    }
}

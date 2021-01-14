package designzen.visitor.test6;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Client.java
 * @createTime 2021-01-12 10:44:00
 */
public class Client {
    public static void main(String[] args) {
        Horse h = new BlackHorse();
        h.eat();
    }
}

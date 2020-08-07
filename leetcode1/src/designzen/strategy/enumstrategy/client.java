package designzen.strategy.enumstrategy;

public class client {
    public static void main(String[] args) {
        System.out.println(Calculator1.ADD.exec(1, 2));
        System.out.println(Calculator2.ADD.exec(1, 3));
    }
}

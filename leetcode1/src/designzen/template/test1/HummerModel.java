package designzen.template.test1;

public abstract class HummerModel {
    public abstract void start();

    public abstract void stop();

    public abstract void alarm();

    public abstract void engineBoom();

    public void run() {
        System.out.println("HummerModel.run");
        start();
        engineBoom();
        alarm();
        stop();
    }
}

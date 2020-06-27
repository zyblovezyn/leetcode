package designzen.template.test1extend;

public abstract class HummerModel {
    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    protected boolean isAlarm() {
        return true;
    }

    public final void run() {
        System.out.println("HummerModel.run");
        start();
        engineBoom();
        if (this.isAlarm())
            alarm();
        stop();
    }
}

package designzen.template.test1extend;

public class HummerH1Model extends HummerModel {

    private boolean alarmFalg = true;

    @Override
    public void start() {
        System.out.println("HummerH1Model.start");
    }

    @Override
    public void stop() {
        System.out.println("HummerH1Model.stop");
    }

    @Override
    public void alarm() {
        System.out.println("HummerH1Model.alarm");
    }

    @Override
    public void engineBoom() {
        System.out.println("HummerH1Model.engineBoom");
    }

    @Override
    protected boolean isAlarm() {
        return this.alarmFalg;
    }

    public void setAlarmFalg(boolean alarmFalg) {
        this.alarmFalg = alarmFalg;
    }
}

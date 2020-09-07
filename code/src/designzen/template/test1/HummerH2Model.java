package designzen.template.test1;

public class HummerH2Model extends HummerModel {
    @Override
    public void start() {
        System.out.println("HummerH2Model.start");
    }

    @Override
    public void stop() {
        System.out.println("HummerH2Model.stop");
    }

    @Override
    public void alarm() {
        System.out.println("HummerH2Model.alarm");
    }

    @Override
    public void engineBoom() {
        System.out.println("HummerH2Model.engineBoom");
    }

}

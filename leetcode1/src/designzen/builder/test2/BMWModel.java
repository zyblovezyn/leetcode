package designzen.builder.test2;

public class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("BMWModel.start");
    }

    @Override
    protected void stop() {
        System.out.println("BMWModel.stop");
    }

    @Override
    protected void alarm() {
        System.out.println("BMWModel.alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("BMWModel.engineBoom");
    }
}

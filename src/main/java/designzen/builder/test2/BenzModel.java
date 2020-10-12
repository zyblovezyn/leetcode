package designzen.builder.test2;

public class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("BenzModel.start");
    }

    @Override
    protected void stop() {
        System.out.println("BenzModel.stop");
    }

    @Override
    protected void alarm() {
        System.out.println("BenzModel.alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("BenzModel.engineBoom");
    }
}

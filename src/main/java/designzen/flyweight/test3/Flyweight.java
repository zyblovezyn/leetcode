package designzen.flyweight.test3;

public abstract class Flyweight {
    // 内部状态
    private String intrinsic;
    //外部状态
    private final String Extrinsic;

    public Flyweight(String extrinsic) {
        Extrinsic = extrinsic;
    }

    public abstract void operate();

    public String getIntrinsic() {
        return intrinsic;
    }

    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}

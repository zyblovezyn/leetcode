package designzen.bridge.test1;

public class ShanZhaiCrop extends Crop {
    public ShanZhaiCrop(Product product) {
        super(product);
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚钱了");
    }
}

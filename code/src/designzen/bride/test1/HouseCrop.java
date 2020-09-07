package designzen.bride.test1;

public class HouseCrop extends Crop {
    public HouseCrop(House product) {
        super(product);
    }

    @Override
    public void makeMoney() {
        super.makeMoney();
        System.out.println("HouseCrop.makeMoney");
    }
}

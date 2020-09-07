package designzen.bride.test1;

public class Client {
    public static void main(String[] args) {
        House house = new House();
        System.out.println("---------- 房地产公司是这样运行的-------------");
        HouseCrop houseCrop = new HouseCrop(house);
        houseCrop.makeMoney();
        System.out.println();

        ShanZhaiCrop shanZhaiCrop = new ShanZhaiCrop(new IPod());
        shanZhaiCrop.makeMoney();

        shanZhaiCrop = new ShanZhaiCrop(new Cloth());
        shanZhaiCrop.makeMoney();
    }
}

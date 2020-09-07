package designzen.factory.test1;

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new HumanFactory();
        WhiteHuman whiteHuman = factory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();

        BlackHuman blackHuman = factory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();

        YellowHuman yellowHuman = factory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}

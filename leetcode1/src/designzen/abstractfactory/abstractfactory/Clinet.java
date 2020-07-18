package designzen.abstractfactory.abstractfactory;

public class Clinet {
    public static void main(String[] args) {
        HumanFactory maleHumanFactory = new MaleFactory();
        Human blackHuman = maleHumanFactory.createBlackHuman();
        blackHuman.getColor();
        blackHuman.getSex();
        blackHuman.talk();
    }
}

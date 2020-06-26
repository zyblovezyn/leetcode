package designzen.factory.simplefactory;

public class Test {
    public static void main(String[] args) {
        System.out.println("--造出的第一批人是白色人种--");
        Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();
//女娲第二次造人， 火候过足， 于是黑人产生了
        System.out.println("\n--造出的第二批人是黑色人种--");
        Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();
//第三次造人， 火候刚刚好， 于是黄色人种产生了
        System.out.println("\n--造出的第三批人是黄色人种--");
        Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}

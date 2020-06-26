package designzen.factory.multifactory;

public class Client {
    public static void main(String[] args) {
        //女娲第一次造人， 火候不足， 于是白色人种产生了
        System.out.println("--造出的第一批人是白色人种--");
        Human whiteHuman = (new WhiteFactory()).createHuman();
        whiteHuman.getColor();
        whiteHuman.talk();
        //女娲第二次造人， 火候过足， 于是黑色人种产生了
        System.out.println("\n--造出的第二批人是黑色人种--");
        Human blackHuman = (new BlackFactory()).createHuman();
        blackHuman.getColor();
        blackHuman.talk();
        //第三次造人， 火候刚刚好， 于是黄色人种产生了
        System.out.println("\n--造出的第三批人是黄色人种--");
        Human yellowHuman = (new YellowFactory()).createHuman();
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}

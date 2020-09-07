package designpattern.factory.SingleFactory.pizafactory;

public class CheesePiza extends Pisa {
    @Override
    public void prepare() {
        System.out.println("准备制作奶酪披萨！");
    }
}

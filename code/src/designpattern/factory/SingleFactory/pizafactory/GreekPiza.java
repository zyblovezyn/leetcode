package designpattern.factory.SingleFactory.pizafactory;

public class GreekPiza extends Pisa {
    @Override
    public void prepare() {
        System.out.println("准备制作希腊披萨！");
    }
}

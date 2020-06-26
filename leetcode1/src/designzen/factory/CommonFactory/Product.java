package designzen.factory.CommonFactory;

public abstract class Product {
    // 产品类的共有方法
    public void method1() {
        //业务逻辑处理
        System.out.println("公用方法！");
    }

    //抽象方法
    protected abstract void method2();
}

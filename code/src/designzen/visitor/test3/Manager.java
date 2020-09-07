package designzen.visitor.test3;

import java.util.Random;

public class Manager extends Staff {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

    //一年做的产品数量
    public int getProducts() {
        return new Random().nextInt(10);
    }
}

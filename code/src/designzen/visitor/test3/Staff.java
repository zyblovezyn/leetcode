package designzen.visitor.test3;

import java.util.Random;

//员工基类
public abstract class Staff {
    public String name;
    public int kpi;

    public Staff(String name) {
        this.name = name;
        kpi=new Random().nextInt(10);
    }

    // 核心方法，接受Visitor的访问
    public abstract void accept(Visitor vistor);
}

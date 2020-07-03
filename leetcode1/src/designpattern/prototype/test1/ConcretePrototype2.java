package designpattern.prototype.test1;

public class ConcretePrototype2 implements Prototype {
    /**
     * @Description: 新建一个自身对象
     * @Param: []
     * @return: java.lang.Object
     * @Author: Mr.Miles
     * @Date: 2020/06/19
     */
    @Override
    public Object clone() {
        Prototype prototype = new ConcretePrototype2();
        return prototype;
    }
}

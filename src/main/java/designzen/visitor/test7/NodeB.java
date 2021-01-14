package designzen.visitor.test7;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName NodeB.java
 * @createTime 2021-01-12 11:11:00
 */
public class NodeB extends Node {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "NodeB";
    }
}

package designzen.visitor.test7;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName VisitorB.java
 * @createTime 2021-01-12 11:15:00
 */
public class VisitorB implements Visitor {
    @Override
    public void visit(NodeA node) {
        System.out.println(node.operationA());
    }

    @Override
    public void visit(NodeB node) {
        System.out.println(node.operationB());
    }
}

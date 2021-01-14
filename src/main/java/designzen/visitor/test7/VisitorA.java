package designzen.visitor.test7;/**
 * @Description TODO
 *<p>
 * @ClassName VisitorA.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2021-01-12 11:10:00
 */
public class VisitorA implements Visitor {
    @Override
    public void visit(NodeA node) {
        System.out.println(node.operationA());
    }

    @Override
    public void visit(NodeB node) {
        System.out.println(node.operationB());
    }
}

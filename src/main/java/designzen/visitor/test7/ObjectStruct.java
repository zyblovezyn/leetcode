package designzen.visitor.test7;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName ObjectStruct.java
 * @createTime 2021-01-12 14:40:00
 */
public class ObjectStruct {
    private List<Node> nodes = new ArrayList<>();

    public void action(Visitor visitor) {
        for (Node node : nodes) {
            node.accept(visitor);
        }
    }

    public void add(Node node) {
        nodes.add(node);
    }
}

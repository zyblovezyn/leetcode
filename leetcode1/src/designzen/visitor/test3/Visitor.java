package designzen.visitor.test3;

public interface Visitor {
    //访问工程师类型
    void visit(Engineer engineer);

    void visit(Manager manager);
}

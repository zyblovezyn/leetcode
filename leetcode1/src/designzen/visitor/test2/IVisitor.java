package designzen.visitor.test2;

public interface IVisitor {
    void visit(CommonEmployee commonEmployee);

    void visit(Manager manager);
}

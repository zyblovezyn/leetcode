package designzen.visitor.common;

public interface IVisitor {
    void visit(ConcreteElement1 commonEmployee);

    void visit(ConcreteElement2 manager);
}

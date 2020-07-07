package designzen.visitor.common;

import designzen.visitor.test2.CommonEmployee;
import designzen.visitor.test2.Manager;

public interface IVisitor {
    void visit(ConcreteElement1 commonEmployee);

    void visit(ConcreteElement2 manager);
}

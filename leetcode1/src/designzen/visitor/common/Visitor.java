package designzen.visitor.common;

import designzen.visitor.test2.CommonEmployee;
import designzen.visitor.test2.Employee;
import designzen.visitor.test2.Manager;

public class Visitor implements IVisitor {
    @Override
    public void visit(ConcreteElement1 e1) {
        e1.doSomething();
    }

    @Override
    public void visit(ConcreteElement2 e2) {
        e2.doSomething();
    }


}

package designzen.mediator.common;

public class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    //自有方法 self-method
    public void selfMethod1() {
        //处理自己的业务
        System.out.println("ConcreteColleague1.selfMethod1");
    }

    //依赖方法 dep-method
    public void depMethod1() {
        //处理自己的业务逻辑
        //自己不能处理的业务委托给中介者
        super.mediator.doSomething1();
    }
}

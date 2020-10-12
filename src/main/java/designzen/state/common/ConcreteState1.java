package designzen.state.common;

public class ConcreteState1 extends State {
    @Override
    public void handle1() {
        System.out.println("ConcreteState1.handle1");
    }

    @Override
    public void handle2() {
        super.context.setCurrentState(Context.STATE2);
        super.context.handle2();
    }
}

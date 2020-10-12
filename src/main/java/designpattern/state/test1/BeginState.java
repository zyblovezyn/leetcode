package designpattern.state.test1;

public class BeginState implements IState {
    @Override
    public void Submit() {
        System.out.println("end---------------");
    }
}

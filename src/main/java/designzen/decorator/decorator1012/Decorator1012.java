package designzen.decorator.decorator1012;

public abstract class Decorator1012 extends Component1012 {
    private Component1012 component1012;

    public Decorator1012(Component1012 component1012) {
        this.component1012 = component1012;
    }

    @Override
    void operate() {
        this.component1012.operate();
    }
}

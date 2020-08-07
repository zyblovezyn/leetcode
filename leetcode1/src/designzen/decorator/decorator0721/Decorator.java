package designzen.decorator.decorator0721;

public abstract class Decorator extends Component {

    private Component component = null;

    //通过构造函数传递被修饰者
    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operator() {
        this.component.operator();
    }
}

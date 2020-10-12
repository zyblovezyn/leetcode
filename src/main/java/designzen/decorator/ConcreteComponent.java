package designzen.decorator;

// 具体构建
public class ConcreteComponent extends Component {
    @Override
    public void operate() {
        System.out.println("ConcreteComponent.operate");
    }
}

package designzen.composite;

import java.util.ArrayList;

public class Composite extends Component {
    //构件容器
    private ArrayList<Component> compoentArrayList = new ArrayList<>();

    //增加一个叶子构件或树枝构件
    public void add(Component compoent) {
        this.compoentArrayList.add(compoent);
    }

    //删除一个叶子构件或树枝构件
    public void remove(Component compoent) {
        this.compoentArrayList.remove(compoent);
    }

    public ArrayList<Component> getChildren() {
        return this.compoentArrayList;
    }

}

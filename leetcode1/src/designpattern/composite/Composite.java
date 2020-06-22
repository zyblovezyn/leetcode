package designpattern.composite;

import java.util.ArrayList;
import java.util.Iterator;

public class Composite implements Component {
    private ArrayList<Component> childrens=new ArrayList<>();

    @Override
    public void add(Component component) {
        childrens.add(component);
    }

    @Override
    public void remove(Component component) {
        childrens.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return childrens.get(i);
    }

    @Override
    public void operation() {
        Iterator<Component> it=childrens.iterator();
        while (it.hasNext()){
            Component component=it.next();
            component.operation();
        }
    }
}

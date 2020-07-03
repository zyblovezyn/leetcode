package designpattern.composite;

public interface Component {
    public  void add(Component component);
    public void remove(Component component);
    public Component getChild(int i);
    public void operation();
}

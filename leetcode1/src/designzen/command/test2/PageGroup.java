package designzen.command.test2;

public class PageGroup extends Group {
    @Override
    public void find() {
        System.out.println("PageGroup.find");
    }

    @Override
    public void delete() {
        System.out.println("PageGroup.delete");
    }

    @Override
    public void change() {
        System.out.println("PageGroup.change");
    }

    @Override
    public void plan() {
        System.out.println("PageGroup.plan");
    }

    @Override
    public void add() {
        System.out.println("PageGroup.add");
    }
}

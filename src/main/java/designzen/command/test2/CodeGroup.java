package designzen.command.test2;

public class CodeGroup extends Group {
    @Override
    public void find() {
        System.out.println("CodeGroup.find");
    }

    @Override
    public void delete() {
        System.out.println("CodeGroup.delete");
    }

    @Override
    public void change() {
        System.out.println("CodeGroup.change");
    }

    @Override
    public void plan() {
        System.out.println("CodeGroup.plan");
    }

    @Override
    public void add() {
        System.out.println("CodeGroup.add");
    }
}

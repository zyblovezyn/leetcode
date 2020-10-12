package designzen.command.test2;

public class RequireGroup extends Group {
    @Override
    public void find() {
        System.out.println("RequireGroup.find");
    }

    @Override
    public void delete() {
        System.out.println("RequireGroup.delete");
    }

    @Override
    public void change() {
        System.out.println("RequireGroup.change");
    }

    @Override
    public void plan() {
        System.out.println("RequireGroup.plan");
    }

    @Override
    public void add() {
        System.out.println("RequireGroup.add");
    }
}

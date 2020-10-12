package designzen.command.test1;

public class Client {
    public static void main(String[] args) {
        Group rg = new RequireGroup();
        rg.find();
        rg.add();
        rg.plan();

        Group group = new PageGroup();
        group.plan();
        group.find();
        group.delete();
    }
}

package designzen.command.test2;

public class Client {
    public static void main(String[] args) {
        Invoker xiaosan=new Invoker();
        Command command = new AddrequirementCOmmand();
        xiaosan.setCommand(command);
        xiaosan.action();

        System.out.println();
        command = new DeletePageCommand();
        xiaosan.setCommand(command);
        xiaosan.action();
    }
}

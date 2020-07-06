package designzen.command.common;

public class ConcreteComand2 extends Command {
    private Receiver receiver;

    public ConcreteComand2(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.doSomething();
    }

}

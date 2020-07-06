package designzen.command.best;

public class ConcreteCommand1 extends Command {
    private Receiver receiver;

    public ConcreteCommand1() {
        super(new ConcreteReceiver1());
    }

    public ConcreteCommand1(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        super.receiver.doSomething();
    }
}


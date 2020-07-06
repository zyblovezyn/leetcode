package designzen.command.best;

import designzen.command.test2.RequireGroup;

public class ConcreteCommand2 extends Command {

    public ConcreteCommand2() {
        super(new ConcreteReceiver2());
    }

    public ConcreteCommand2(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        super.receiver.doSomething();
    }

}

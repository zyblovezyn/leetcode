package designzen.visitor.test3;

import java.util.Random;

public class Engineer extends Staff {
    public Engineer(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

    public int getCodeLines() {
        return new Random().nextInt(10*10000);
    }
}

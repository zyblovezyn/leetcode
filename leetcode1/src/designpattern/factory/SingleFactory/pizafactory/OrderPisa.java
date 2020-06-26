package designpattern.factory.SingleFactory.pizafactory;

public class OrderPisa {
    public OrderPisa() {
        Pisa piza = null;
        String pisaType;
        do {
            pisaType = "greek";
            if (pisaType.equals("greek")) {
                piza = new GreekPiza();
            } else if (pisaType.equals("cheese")) {
                piza = new CheesePiza();
            } else {
                break;
            }

        } while (true);
    }
}

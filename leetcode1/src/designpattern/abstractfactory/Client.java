package designpattern.abstractfactory;

public class Client {
    public static void main(String[] args) {
        ComputerEngineer computerEngineer=new ComputerEngineer();
        computerEngineer.makeComputer(1,2);

    }
}

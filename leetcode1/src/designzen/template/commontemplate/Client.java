package designzen.template.commontemplate;

public class Client {
    public static void main(String[] args) {
        AbstarctTemplate template1 = new ConcreteTemplate1();
        template1.templateMethod();
    }
}

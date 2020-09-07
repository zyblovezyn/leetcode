package designzen.doorface.test1;

public class Client {
    public static void main(String[] args) {
        ILetterProcess letterProcess = new LetterProcessImpl();
        letterProcess.writeContext("123");
        letterProcess.fillEnvelope("happy");
        letterProcess.letterInotoEnvelope();
        letterProcess.sendLetter();


        ModenPostOffice helloModenPostOffice=new ModenPostOffice();
        String address="Happy Road No.666";
        String context = "hello,It's me,do you know who i am?";
        helloModenPostOffice.sendLetter(context,address);
    }
}

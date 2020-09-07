package designzen.doorface.test1;

public class ModenPostOffice {
    private ILetterProcess letterProcess = new LetterProcessImpl();

    public void sendLetter(String context, String address) {
        letterProcess.writeContext(context);
        letterProcess.fillEnvelope(address);
        letterProcess.letterInotoEnvelope();
        letterProcess.sendLetter();
    }
}

package designzen.doorface.test1;

public class LetterProcessImpl implements ILetterProcess {
    @Override
    public void writeContext(String context) {
        System.out.println("LetterProcessImpl.writeContext");
    }

    @Override
    public void fillEnvelope(String address) {
        System.out.println("LetterProcessImpl.fillEnvelope");
    }

    @Override
    public void letterInotoEnvelope() {
        System.out.println("LetterProcessImpl.letterInotoEnvelope");
    }

    @Override
    public void sendLetter() {
        System.out.println("LetterProcessImpl.sendLetter");
    }
}

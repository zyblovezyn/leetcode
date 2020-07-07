package designzen.doorface.test1;

public interface ILetterProcess {
    void writeContext(String context);

    void fillEnvelope(String address);

    void letterInotoEnvelope();

    void sendLetter();
}

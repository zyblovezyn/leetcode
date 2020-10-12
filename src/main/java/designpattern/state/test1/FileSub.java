package designpattern.state.test1;

public class FileSub {
    private IState iState;

    public FileSub(IState iState) {
        this.iState = iState;
    }

    public void setState(IState iState) {
        this.iState = iState;
    }

    public void Submit() {
        iState.Submit();
    }
}

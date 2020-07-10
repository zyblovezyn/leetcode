package designzen.state;

public class Lift implements IList {
    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {
        switch (this.state) {
            case OPENING_STATE:
                this.closeWithoutLogic();
                this.setState(CLOSING_STATE);
                break;
            case CLOSING_STATE:
                break;
            case RUNNING_STATE:
                break;
            case STOPPING_STATE:
                break;
        }

    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }

    //纯粹的电梯关门，不考虑实际的逻辑
    private void closeWithoutLogic() {
        System.out.println("电梯门关闭...");
    }

    //纯粹的电梯开门，不考虑任何条件
    private void openWithoutLogic() {
        System.out.println("电梯门开启...");
    }

    //纯粹的运行，不考虑其他条件
    private void runWithoutLogic() {
        System.out.println("电梯上下运行起来...");
    }

    //单纯的停止，不考虑其他条件
    private void stopWithoutLogic() {
        System.out.println("电梯停止了...");
    }
}

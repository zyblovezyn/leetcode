package designzen.state;

public interface IList {
    //电梯的4个状态
    int OPENING_STATE = 1;  //敞门状态
    int CLOSING_STATE = 2;  //闭门状态
    int RUNNING_STATE = 3;  //运行状态
    int STOPPING_STATE = 4; //停止状态

    void setState(int state);

    void open();

    void close();

    void run();

    void stop();

}

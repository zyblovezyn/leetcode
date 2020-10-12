package designzen.builder.test2;

import java.util.ArrayList;

public abstract class CarModel {
    private ArrayList<String> sequence = new ArrayList<>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    public final void run() {
        for (int i = 0; i < sequence.size(); i++) {
            String s = sequence.get(i);
            s = s.toLowerCase();
            switch (s) {
                case "start":
                    start();
                    break;
                case "alarm":
                    alarm();
                    break;
                case "engineboom":
                    engineBoom();
                    break;
                case "stop":
                    stop();
                    break;
                default:
                    break;
            }
        }
    }

    public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }
}


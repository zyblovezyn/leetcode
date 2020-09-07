package designzen.builder;

import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        CarModel carModel = new BenzModel();
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("start");
        sequence.add("engineBoom");
        sequence.add("alarm");
        sequence.add("stop");
        carModel.setSequence(sequence);
        carModel.run();
    }
}

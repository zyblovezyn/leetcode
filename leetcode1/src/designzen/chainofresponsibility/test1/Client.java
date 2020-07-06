package designzen.chainofresponsibility.test1;

import java.util.ArrayList;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<IWoman> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList.add(new Woman(random.nextInt(4), "我要去逛街"));
        }
        IHandler father = new Father();
        IHandler husband = new Husband();
        IHandler son = new Son();
        for (IWoman woman : arrayList) {
            if (woman.getType() == 1) {
                father.HandleMessage(woman);
            } else if (woman.getType() == 2) {
                husband.HandleMessage(woman);
            } else if (woman.getType() == 3) {
                son.HandleMessage(woman);
            } else {

            }
        }
    }
}

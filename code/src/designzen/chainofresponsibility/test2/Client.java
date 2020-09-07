package designzen.chainofresponsibility.test2;

import java.util.ArrayList;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<IWoman> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList.add(new Woman(random.nextInt(4), "我要去逛街"));
        }
        Handler father = new Father();
        Handler husband=new Husband();
        Handler son = new Son();

        father.setNext(husband);
        husband.setNext(son);
        for (IWoman woman : arrayList) {
            father.HandleMessage(woman);
        }
    }
}

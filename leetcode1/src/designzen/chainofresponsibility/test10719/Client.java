package designzen.chainofresponsibility.test10719;

import java.util.ArrayList;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        //随机挑选几个女性
        Random rand = new Random();
        ArrayList<IWoman> arrayList = new ArrayList();
        for(int i=0;i<5;i++){
            arrayList.add(new Woman(rand.nextInt(4),"我要出去逛街"));
        }
        //定义三个请示对象
        Handler father = new Father();
        Handler husband = new Husband();
        Handler son = new Son();
        //设置请示顺序
        father.setNext(husband);
        husband.setNext(son);
        for(IWoman women:arrayList){
            father.handleMessage(women);}
    }
}

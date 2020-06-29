package designzen.proxy.dynamicproxy;

import designzen.proxy.test1.IGamePlayer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        InvocationHandler hanlder = new GamePlayIH(player);
        ClassLoader cl = player.getClass().getClassLoader();
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(cl, new Class[]{IGamePlayer.class}, hanlder);
        proxy.logon("zhangsan", "password");
        proxy.killBoss();
        proxy.upgrade();
    }
}

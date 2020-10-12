package designzen.proxy.dynamicproxy;

import designzen.proxy.test1.IGamePlayer;

public class GamePlayer implements IGamePlayer {
    private String name;

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void logon(String name, String password) {
        System.out.print("name = " + name);
        System.out.print("password = " + password);
        System.out.println("登录成功！");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "打怪升级");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "在更新");
    }
}

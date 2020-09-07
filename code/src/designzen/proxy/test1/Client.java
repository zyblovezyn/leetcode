package designzen.proxy.test1;

public class Client {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        player.logon("zhangsan", "password");
        player.killBoss();
        player.upgrade();

        GamePlayerProxy gamePlayerProxy = new GamePlayerProxy(player);
        gamePlayerProxy.logon("zhangsan", "password");
        gamePlayerProxy.killBoss();
        gamePlayerProxy.upgrade();
    }
}

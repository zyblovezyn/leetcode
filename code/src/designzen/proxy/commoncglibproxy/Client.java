package designzen.proxy.commoncglibproxy;

public class Client {
    public static void main(String[] args) {
        //ñ⁄??è€
        UserDao target = new UserDao();
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}

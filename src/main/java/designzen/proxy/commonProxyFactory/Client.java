package designzen.proxy.commonProxyFactory;

public class Client {
    public static void main(String[] args) {
        //��??��
        IUserDao target = new UserDao();
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}

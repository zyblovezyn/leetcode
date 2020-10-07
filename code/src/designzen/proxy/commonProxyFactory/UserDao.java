package designzen.proxy.commonProxyFactory;

public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("-------------saved date----------------");
    }
}

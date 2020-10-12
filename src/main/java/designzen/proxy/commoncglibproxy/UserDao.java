package designzen.proxy.commoncglibproxy;

import designzen.proxy.commonProxyFactory.IUserDao;

public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("-------------saved date----------------");
    }
}

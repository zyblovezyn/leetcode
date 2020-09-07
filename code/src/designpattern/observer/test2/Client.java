package designpattern.observer.test2;

public class Client {
    public static void main(String[] args) {
        Subject mSubscriptionSubject=new ConcreteSubject();
        //创建微信用户
        User user1=new User("杨影枫");
        User user2=new User("月眉儿");
        User user3=new User("紫轩");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("刘望舒的专栏更新了");

    }
}

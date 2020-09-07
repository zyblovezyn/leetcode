package designzen.factory.simplefactory;

public class HumanFactory {
    public static <T extends Human> T createHuman(Class<T> tClass) {
        Human human = null;
        try {
            human = (T) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人中生成错误");
        }
        return (T) human;
    }
}

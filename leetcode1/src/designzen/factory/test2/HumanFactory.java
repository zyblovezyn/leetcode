package designzen.factory.test2;

public class HumanFactory extends AbstractHumanFactory {

    @Override
    public <T extends Human> T createHuman(Class<T> tClass) {
        Human human = null;
        try {
            human = (T) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人中生成错误");
        }
        return (T) human;
    }
}

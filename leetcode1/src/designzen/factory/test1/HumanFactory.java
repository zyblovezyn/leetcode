package designzen.factory.test1;

public class HumanFactory extends AbstractFactory {
    @Override
    public <T extends Human> T createFactory(Class<T> tClass) {
        Human human = null;
        try {
            human = (T) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) human;
    }
}

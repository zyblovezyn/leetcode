package designzen.factory.test1;

public abstract class AbstractFactory {
    public abstract <T extends Human> T createFactory(Class<T> tClass);
}

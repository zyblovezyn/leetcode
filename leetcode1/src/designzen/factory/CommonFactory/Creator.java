package designzen.factory.CommonFactory;

public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> tClass);
}

package designzen.abstractfactory.commonfactory02;

public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> tClass);
}

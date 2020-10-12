package designzen.abstractfactory.commonfactorytest;

public interface Creator {
    <T extends Product> T createProduct(Class<T> tClass);
}

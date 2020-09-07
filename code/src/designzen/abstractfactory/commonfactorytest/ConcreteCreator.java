package designzen.abstractfactory.commonfactorytest;

public class ConcreteCreator implements Creator {
    @Override
    public <T extends Product> T createProduct(Class<T> tClass) {
        Product product = null;
        try {
            product = (T) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}

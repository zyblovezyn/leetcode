package designzen.abstractfactory.commonfactory02;

public class CreateFactory extends Creator {
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

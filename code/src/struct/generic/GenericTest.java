package struct.generic;

import com.sun.org.glassfish.gmbal.AMXMBeanInterface;

public class GenericTest<T> {
    private T key;

    public GenericTest(T key) {
        this.key = key;
    }

    public T getKey() {
        return this.key;
    }

    public static void main(String[] args) {
        GenericTest<Integer> genericTest = new GenericTest<>(123456);
        GenericTest<String> genericString = new GenericTest<>("key_value");
        System.out.println("key is " + genericTest.getKey());
        System.out.println("key is "+genericString.getKey());
    }
}

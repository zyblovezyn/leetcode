package struct.generic;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        // ?Œš HashMap ?Û Sites
        HashMap<Integer, String> Sites = new HashMap<Integer, String>();
        // “Y‰Á???
        Sites.put(1, "Google");
        Sites.put(2, "Runoob");
        Sites.put(3, "Taobao");
        Sites.put(4, "Zhihu");
        Sites.remove(4);
        System.out.println(Sites);
    }
}

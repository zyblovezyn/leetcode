package designpattern.flyweight;

import java.util.HashMap;

public class FlyweightFactory {

    private static HashMap<String, Flyweight> pool = new HashMap<>();

    public static Flyweight getFlyweight(String extrinsic) {
        Flyweight flyweight = null;

        if (pool.containsKey(extrinsic)) {
            flyweight = pool.get(extrinsic);
        } else {
            flyweight = new ConcreteFlyweight(extrinsic);
            pool.put(extrinsic, flyweight);
        }
        return flyweight;
    }
}

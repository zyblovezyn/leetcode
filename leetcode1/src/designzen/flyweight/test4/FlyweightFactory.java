package designzen.flyweight.test4;

import java.util.HashMap;

public class FlyweightFactory {
    private static HashMap<String, Flyweight> flyweightPool = new HashMap<>();

    public static Flyweight getFlyweight(String outSide) {
        Flyweight flyweight = null;
        if (flyweightPool.containsKey(outSide)) {
            flyweight = flyweightPool.get(outSide);
        } else {
            flyweight = new ConcreteFlyweight(outSide);
            flyweightPool.put(outSide, flyweight);
        }
        return flyweight;
    }
}

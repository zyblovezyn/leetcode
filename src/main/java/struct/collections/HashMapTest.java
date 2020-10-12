package struct.collections;

import utils.CommonUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            maps.put(i, i);
        }

        CommonUtils.startTime();
        for (Integer key : maps.keySet()) {
            Integer temp = key;
        }
        CommonUtils.endTime();
        CommonUtils.startTime();

        Iterator<Map.Entry<Integer, Integer>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()) {
            int temp = iterator.next().getKey();
        }
        CommonUtils.endTime();


        CommonUtils.startTime();

        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            int temp = entry.getKey();
        }
        CommonUtils.endTime();


        CommonUtils.startTime();

        for (Integer v : maps.values()) {
            int temp = v;
        }
        CommonUtils.endTime();


    }
}

package struct.collections;

import utils.WatcherTime;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < 2000000; i++) {
            maps.put(i, i);
        }

        WatcherTime.start();
        for (Integer key : maps.keySet()) {
            maps.get(key);
        }
        WatcherTime.stop();
        WatcherTime.print();

        WatcherTime.start();
        Iterator<Map.Entry<Integer, Integer>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().getValue();
        }
        WatcherTime.stop();
        WatcherTime.print();

        WatcherTime.start();
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            entry.getValue();
        }
        WatcherTime.stop();
        WatcherTime.print();

        WatcherTime.start();
        for (Integer v : maps.values()) {
            int temp = v;
        }
        WatcherTime.stop();
        WatcherTime.print();


    }
}

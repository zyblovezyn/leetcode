package temp;

import utils.WatcherTime;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description 四种遍历方法比较
 * @author Miles
 * @version 1.0.0
 * @ClassName HashMap4Iterator.java
 * @createTime 2020-10-22 19:46:00
 */
public class HashMap4Iterator {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 2000000; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }


        WatcherTime.start();
        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            String value = map.get(key);
//            System.out.println("key= " + key + " and value= " + map.get(key));
        }
        WatcherTime.stop();
        WatcherTime.print();

        WatcherTime.start();
        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String value = entry.getValue();
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        WatcherTime.stop();
        WatcherTime.print();

        WatcherTime.start();
        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        WatcherTime.stop();
        WatcherTime.print();

        WatcherTime.start();
        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            String value = v;
//            System.out.println("value= " + v);
        }
        WatcherTime.stop();
        WatcherTime.print();
    }
}

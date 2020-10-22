package temp;

import java.util.*;

/**
 * @author Miles
 * @version 1.0.0
 * @ClassName TreeMapTest.java
 * @Description TODO
 * @createTime 2020-10-22 18:25:00
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        System.out.println("初始化后,TreeMap元素个数为：" + treeMap.size());
        //新增元素:
        treeMap.put("hello", 1);
        treeMap.put("world", 2);
        treeMap.put("my", 3);
        treeMap.put("name", 4);
        treeMap.put("is", 5);
        treeMap.put("jiaboyan", 6);
        treeMap.put("i", 6);
        treeMap.put("am", 6);
        treeMap.put("a", 6);
        treeMap.put("developer", 6);
        System.out.println("添加元素后,TreeMap元素个数为：" + treeMap.size());

        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        for (Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println("TreeMap的key："+key+",value="+value);
        }

        Set<String> keySet = treeMap.keySet();
        for(String keyItem:keySet) {
            System.out.println(keyItem);
        }

        Collection<Integer> valueList = treeMap.values();
        for (Integer valueItem : valueList) {
            System.out.println(valueItem);
        }

        Integer getValue = treeMap.get("jiaboyan");
        String firstKey = treeMap.firstKey();
        String lastKey = treeMap.lastKey();
        String lowerKey = treeMap.lowerKey("jaiboyan");
        String ceilingKey = treeMap.ceilingKey("jiaboyan");
        SortedMap<String, Integer> sortedMap = treeMap.subMap("a", "my");

        Integer removeValue = treeMap.remove("jiaboyan");
        treeMap.clear();

        boolean isEmpty = treeMap.isEmpty();
        boolean isContain = treeMap.containsKey("jiaboyan");
    }
}


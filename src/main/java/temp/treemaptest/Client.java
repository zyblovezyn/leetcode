package temp.treemaptest;

import java.util.TreeMap;

/**
 * @author Miles
 * @version 1.0.0
 * @ClassName Client.java
 * @Description
 * @createTime 2020-10-22 18:55:00
 */
public class Client {
    public static void main(String[] args) {
        customSort();
    }

    public static void customSort() {
        TreeMap<SortedTest, String> treeMap = new TreeMap<>(new SortedTestComparator());
        treeMap.put(new SortedTest(10), "hello");
        treeMap.put(new SortedTest(21), "my");
        treeMap.put(new SortedTest(15), "name");
        treeMap.put(new SortedTest(2), "zhangyuanbo");
        treeMap.put(new SortedTest(7), "world");
        System.out.println("treeMap.toString() = " + treeMap.toString());
    }
}

package struct.hashSetTest;

import java.util.HashSet;
import java.util.Iterator;

public class hashSet01 {
    public static void main(String[] args) {
        HashSet<String> setString = new HashSet<>();
        setString.add("星期一");
        setString.add("星期二");
        setString.add("星期三");
        setString.add("星期四");
        setString.add("星期五");

        // 1. hashSet 遍历的顺序并非是插入的顺序
        Iterator<String> iterator = setString.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

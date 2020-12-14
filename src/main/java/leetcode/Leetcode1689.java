package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode1689.java
 * @createTime 2020-12-14 15:42:00
 */
public class Leetcode1689 {
    public int minPartitions(String n) {
        int a = Integer.parseInt(n);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            String str = Integer.toBinaryString(i);
            if (str.startsWith("0")) {
                continue;
            } else {
                list.add(i);
            }
        }
        return list.size();
    }
}

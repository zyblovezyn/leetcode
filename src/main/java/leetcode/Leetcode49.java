package leetcode;

import java.util.*;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode49.java
 * @createTime 2020-12-14 16:20:00
 */
public class Leetcode49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] chs=str.toCharArray();
            Arrays.sort(chs);
            String key=new String(chs);
            List<String> list=hashMap.getOrDefault(key,new ArrayList<>());
            list.add(str);
            hashMap.put(key,list);
        }
        return new ArrayList<List<String>>(hashMap.values());
    }
}

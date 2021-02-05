package leetcode.lcode1_50;

import utils.StringUtils;

import java.util.HashSet;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName leetcode_.java
 * @createTime 2021-01-22 14:27:00
 */
public class leetcode_3 {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet = new HashSet<Character>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                boolean isDuplicate = hashSet.add(s.charAt(j));
                if (!isDuplicate) {
                    if (hashSet.size() > count) {
                        count = hashSet.size();
                    }
                    hashSet.clear();
                    break;
                }
            }
            if(hashSet.size()>count){
                count = hashSet.size();
            }
        }
        return count;
    }
}

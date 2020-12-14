package leetcode;

import java.util.HashSet;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode1684.java
 * @createTime 2020-12-14 17:39:00
 */
public class Leetcode1684 {
    public int countConsistentStrings(String allowed, String[] words) {
        int count = 0;
        HashSet<Character> hashSet = new HashSet();
        for (int i = 0; i < allowed.length(); i++) {
            hashSet.add(allowed.charAt(i));
        }
        for (String word : words) {
            boolean isConsistent = true;
            for (int i = 0; i < word.length(); i++) {
                if(hashSet.contains(word.charAt(i))){
                    continue;
                }else {
                    if (isConsistent(word.charAt(i), allowed)) {
                        continue;
                    } else {
                        isConsistent = false;
                        break;
                    }
                }
            }
            if (isConsistent) {
                count++;
            }
        }
        return count;
    }

    private boolean isConsistent(char s, String allowed) {
        for (char c : allowed.toCharArray()) {
            if (s == c) {
                return true;
            }
        }
        return false;
    }
}

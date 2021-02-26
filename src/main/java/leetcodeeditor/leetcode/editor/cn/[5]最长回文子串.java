package leetcodeeditor.leetcode.editor.cn;
//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3241 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    public String longestPalindrome(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                if (maxLength < (i - hashMap.get(s.charAt(i)))) {
                    startIndex = hashMap.get(s.charAt(i));
                    endIndex=i;
                    hashMap.remove(s.charAt(i));
                }
            }else {
                hashMap.put(s.charAt(i), i);
            }
        }
        if (hashMap.size() == 1) {
            return String.valueOf(s.charAt(0));
        }
        return s.substring(startIndex, endIndex + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

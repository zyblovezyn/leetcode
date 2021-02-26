package leetcodeeditor.leetcode.editor.cn;//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1474 👎 0


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        boolean isBreak = false;
        for (int i = 0; i < 201; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    isBreak = true;
                    break;
                }
                Character key = strs[j].charAt(i);
                if (hashMap.containsKey(key)) {
                    hashMap.replace(key, hashMap.get(key) + 1);
                } else {
                    hashMap.put(key, 1);
                }
            }
            if (isBreak) {
                break;
            }
        }
        for (Character key : hashMap.keySet()) {
            if (hashMap.get(key) == strs.length) {
                stringBuilder.append(key);
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

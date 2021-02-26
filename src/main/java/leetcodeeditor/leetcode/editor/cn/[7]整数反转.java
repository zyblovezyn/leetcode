package leetcodeeditor.leetcode.editor.cn;//给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -231 <= x <= 231 - 1 
// 
// Related Topics 数学 
// 👍 2553 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution7 {
    public int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            rev = rev * 10 +  x % 10;
            x = x / 10;
        }
        if (rev >= Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) rev;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

package leetcodeeditor.leetcode.editor.cn;
//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3728 👎 0


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args) {
        int nums1[] = new int[2];
        nums1[0] = 1;
        nums1[1] = 3;
        int nums2[] = new int[1];
        nums2[0] = 2;
        findMedianSortedArrays(nums1, nums2);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int mid1;
        int mid2;
        int midLength = length / 2;
        if (length % 2 == 0) {
            mid1 = midLength - 1;
            mid2 = midLength;
        } else {
            mid1 = midLength;
            mid2 = midLength;
        }

        int startIndexNum1 = 0;
        int startIndexNum2 = 0;
        int pre = 0;
        int next = 0;
        while (true) {
            pre = next;
            if (nums1[startIndexNum1] > nums2[startIndexNum2]) {
                next = nums2[startIndexNum2];
                startIndexNum2++;
            } else {
                next = nums1[startIndexNum1];
                startIndexNum1++;
            }
            midLength--;
            if (midLength == -1) {
                break;
            }
        }

        if (mid1 == mid2) {
            System.out.println(next);
            return next;
        } else {
            System.out.println(((double) (pre + next)) / 2);
            return   ((double) (pre + next)) / 2;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

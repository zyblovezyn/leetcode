package leetcode.lcode700_750;

import java.util.Arrays;

/**
 * @Description TODO
 *<p>
 * @ClassName leetcode724.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2021-01-28 19:10:00
 */
public class leetcode724 {
    public static int pivotIndex(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}

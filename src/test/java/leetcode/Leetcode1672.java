package leetcode;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode1672.java
 * @createTime 2020-12-14 20:05:00
 */
public class Leetcode1672 {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        int tmp = 0;
        for (int[] account : accounts) {
            for (int i : account) {
                tmp += i;
            }
            tmp = 0;
            if (tmp > max) {
                max = tmp;
            }
        }
        return max;
    }
}

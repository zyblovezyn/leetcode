package leetcode;

/**
 * @Description TODO
 *<p>
 * @ClassName leetcode1311.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2020-12-12 18:55:00
 */
public class Leetcode1313 {
    public int[] decompressRLElist(int[] nums) {
        int length=0;
        for (int i = 0; i < nums.length; i=i+2) {
            length+=nums[i];
        }
        int[] arr=new int[length];
        int arrindex=0;
        for (int i = 0; i < nums.length; i=i+2) {
            int nextIndex=i+1;
            int value=nums[nextIndex];
            for (int j = 0; j < nums[i]; j++) {
                arr[arrindex++]=value;
            }
        }
        return arr;
    }
}

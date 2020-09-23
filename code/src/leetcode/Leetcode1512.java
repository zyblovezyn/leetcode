package leetcode;

public class Leetcode1512 {
    public int numIdenticalPairs(int[] nums) {
        int goodNum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] ^ nums[j]) == 0) {
                    goodNum++;
                }
            }
        }
        return goodNum;
    }
}

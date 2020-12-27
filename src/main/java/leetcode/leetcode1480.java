package leetcode;

public class leetcode1480 {
    public static void main(String[] args) {
        System.out.println("start ...");
        Solution solution=new Solution();
        int[] arrs={3,1,2,10,1};
        solution.runningSum(arrs);
        System.out.println("end ... ");
    }
}

class Solution {
    public int[] runningSum(int[] nums) {
        int sum=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
            nums[i]=sum;
        }
        return nums;
    }
}

import java.util.LinkedList;

public class leetcode1470 {
    public int[] shuffle(int[]  nums, int n) {
        int[] resNums=new int[2*n];
        int left=0;
        int right=n;
        for (int i=0;i<2*n;i++){
             if(i%2==0)
                 resNums[i]=nums[left++];
             else
                 resNums[i]=nums[right++];
        }
         return resNums;
    }
}

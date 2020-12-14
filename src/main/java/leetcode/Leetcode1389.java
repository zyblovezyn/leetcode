package leetcode;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Lettcode1389.java
 * @createTime 2020-12-12 19:31:00
 */
public class Leetcode1389 {
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] ret = new int[index.length];
        int retIndex = 0;
        for (int i = 0; i < index.length; i++) {
            if (index[i] < retIndex) {
                for (int j = retIndex ; j >= index[i]; j--) {
                    if(j-1<0){
                        break;
                    }
                    ret[j] = ret[j - 1];
                }
                ret[index[i]] = nums[i];
                retIndex++;
            } else {
                ret[retIndex++] = nums[i];
            }
        }
        return ret;
    }
}

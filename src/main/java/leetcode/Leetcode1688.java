package leetcode;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode1688.java
 * @createTime 2020-12-14 16:08:00
 */
public class Leetcode1688 {
    public int numberOfMatches(int n) {
        int matches = 0;
        if (n % 2 != 0) {
            n = n + 1;
            matches = -1;
        }
        while (n > 1) {
            if (n % 2 != 0) {
                n = n + 1;
                matches =matches -1;
            }
            n = n / 2;
            matches += n;
        }
        return  matches;
    }
}

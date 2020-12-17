package leetcode;


/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode738.java
 * @createTime 2020-12-15 11:01:00
 */
public class Leetcode738 {
    public int monotoneIncreasingDigits(int N) {
        for (int i = N; i > 0; i--) {
            char[] chars = String.valueOf(i).toCharArray();
            for (int j = 0; j < chars.length - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    chars[j] = String.valueOf(Character.getNumericValue(chars[j]) - 1).charAt(0);
                    for (int k = j + 1; k < chars.length; k++) {
                        chars[k] = '9';
                    }
                    i = Integer.parseInt(new String(chars));
                    break;
                }
            }
            if (isIncreasingDigits(chars)) {
                return i;
            }
        }
        return 0;
    }

    private boolean isIncreasingDigits(char[] chars) {
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] > chars[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

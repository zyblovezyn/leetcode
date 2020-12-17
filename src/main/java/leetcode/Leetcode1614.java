package leetcode;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode1614.java
 * @createTime 2020-12-15 19:45:00
 */
public class Leetcode1614 {
    public int maxDepth(String s) {
        int maxDepth = 0;
        int temp = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                temp++;
                if (temp > maxDepth) {
                    maxDepth = temp;
                }
            }
            if (c == ')') {
                temp--;
            }
        }
        return maxDepth;
    }

    public int game(int[] guess, int[] answer) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if ((guess[i] ^ answer[i]) == 0) {
                count++;
            }
        }
        return count;
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < word1.length; i++) {
            stringBuilder1.append(word1[i]);
        }
        for (int i = 0; i < word2.length; i++) {
            stringBuilder2.append(word2[i]);
        }
        return stringBuilder1.toString().equals(stringBuilder2.toString());
    }
}

package leetcode;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Leetcode1678.java
 * @createTime 2020-12-14 20:13:00
 */
public class Leetcode1678 {
    public String interpret(String command) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            char item = command.charAt(i);
            if (item == 'G') {
                stringBuilder.append("G");
            } else if (item == '(') {
                item = command.charAt(i + 1);
                if (item == ')') {
                    stringBuilder.append("o");
                    i++;
                } else {
                    stringBuilder.append("al");
                    i = i + 3;
                }
            }
        }
        return stringBuilder.toString();
    }
}

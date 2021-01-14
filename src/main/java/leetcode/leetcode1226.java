package leetcode;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName leetcode1226.java
 * @createTime 2021-01-12 16:12:00
 */
public class leetcode1226 {

    public static void main(String[] args) {
        restoreString("codeleet", new int[]{4, 5, 6, 7, 0, 2, 1, 3});
    }

    public static String restoreString(String s, int[] indices) {
        char[] character = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            character[i] = s.charAt(indices[i]);
        }
        return String.valueOf(character);
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int sum = 0;
        for (int i = 1; i < points.length; i++) {
            sum += Math.max(Math.abs(points[i][1] - points[i - 1][1]), Math.abs(points[i][0] - points[i - 1][0]));
        }
        return sum;
    }

    public int minPartitions(String n) {
        int max = n.charAt(0);
        for (int i = 1; i < n.length(); i++) {
            if(max==57){
                break;
            }
            int temp = n.charAt(i);
            if(temp>max){
                max=temp;
            }
        }
        return max - 48;
    }
}

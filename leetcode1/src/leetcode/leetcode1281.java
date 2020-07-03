package leetcode;

public class leetcode1281 {
    public static void main(String[] args) {
    }

    public static int subtractProductAndSum(int n) {
        String str = String.valueOf(n);
        int sum = 0;
        int cal = 1;
        int temp = 0;
        for (Character c : str.toCharArray()) {
            temp = Character.getNumericValue(c);
            sum += temp;
            cal *= temp;
        }
        return cal - sum;
    }

    public static int subtractProductAndSum1(int n) {
        int sum = 0;
        int cal = 1;
        int temp = 0;
        while (n > 0) {
            temp = n % 10;
            cal *= temp;
            sum += temp;
            n /= 10;
        }
        return cal - sum;
    }
}

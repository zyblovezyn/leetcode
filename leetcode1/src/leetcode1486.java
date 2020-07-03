public class leetcode1486 {
    public static void main(String[] args) {
        System.out.println("xo = " + xorOperation(5, 0));
        System.out.println("xorOperation(4,3) = " + xorOperation(4, 3));
        System.out.println("xorOperation(1,7) = " + xorOperation(1, 7));
        System.out.println("xorOperation(10,5) = " + xorOperation(10, 5));
        System.out.println("0^12222 = " + (0 ^ 12222));
        System.out.println("1^111111 = " + (1 ^ 111111));
        System.out.println("(123^123) = " + (123 ^ 123));

        System.out.println("(12^3^12 = " + (12^3^12));
    }

    public static int xorOperation(int n, int start) {
        int res = start;
        for (int i = 1; i < n; i++) {
            res = res ^ (start + 2 * i);
        }
        return res;
    }
}

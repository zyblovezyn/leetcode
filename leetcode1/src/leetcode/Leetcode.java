package leetcode;

import java.util.Arrays;

public class Leetcode {
    public static void main(String[] args) {
        System.out.println(123);
        int[] arrs = new int[10];
        for (int i = 0; i < 10; i++) {
            arrs[i] = i;
        }

       /* int[] arrs1=Arrays.copyOf(arrs,5);
        for (int i = 0; i < arrs1.length; i++) {
            System.out.println("i = " + arrs1[i]);
        }*/
      /* int[] arrs1=Arrays.copyOfRange(arrs,2,5);
        for (int i = 0; i < arrs1.length; i++) {
            System.out.println(arrs1[i]);
        }*/

        String str = "abcedfg";
        System.out.println(str.codePointAt(0));
    }
}

package struct.collections;

import java.util.Arrays;

public class systemarraycopy {
    public static void main(String[] args) {

        int[] b1=new int[8];
        Arrays.fill(b1,10);
        System.out.println(Arrays.toString(b1));
        Arrays.fill(b1,10);
        System.out.println(Arrays.toString(b1));
        Arrays.fill(b1,1);
        System.out.println(Arrays.toString(b1));
        Arrays.fill(b1,2);
        System.out.println(Arrays.toString(b1));
        Arrays.fill(b1,3);
        System.out.println(Arrays.toString(b1));
        Arrays.fill(b1,4);
        System.out.println(Arrays.toString(b1));
        Arrays.fill(b1, 5);
        System.out.println(Arrays.toString(b1));
        Arrays.fill(b1, 6);
        System.out.println(Arrays.toString(b1));

        // testfill();
        int a[] = new int[1000];
        Arrays.fill(a, 250);

        long start, end, times;
        times = 10000000;

        start = System.currentTimeMillis();
        {
            int b[] = new int[10];
            for (int i = 0; i < times; i++) {
                for (int j = 0; j < b.length; j++)
                    b[j] = a[j];
            }
            end = System.currentTimeMillis();
            System.out.println("Loop directly=" + (end - start));
            System.out.println("b[]=" + Arrays.toString(b));
        }

        start = System.currentTimeMillis();
        {
            int b[] = new int[10];
            for (int i = 0; i < times; i++) {
                System.arraycopy(a, 0, b, 0, b.length);
            }
            end = System.currentTimeMillis();
            System.out.println("System.arraycopy=" + (end - start));
            System.out.println("b[]=" + Arrays.toString(b));
        }

        System.arraycopy(a,0,b1,0,Math.min(a.length,b1.length));
    }
}

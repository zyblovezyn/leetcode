package algorithm;

/**
 * @author Miles
 * @version 1.0.0
 * @Description shell排序算法
 * <p>
 * @ClassName ShellSort.java
 * @createTime 2020-12-12 11:25:00
 */
public class ShellSort {
    public static void ShellSort(int[] ints) {
        int length = ints.length;
        for (int step = length / 2; step > 0; step = step / 2) {
            for (int i = step; i < length; i++) {
                int j = i;
                while (j - step >= 0 && ints[j - step] > ints[j]) {
                    swap(ints, j, j - step);
                    j -= step;
                }
            }
        }
    }

    public static void swap(int[] ints, int a, int b) {
        int tmp = ints[a];
        ints[a] = ints[b];
        ints[b] = tmp;
    }
}

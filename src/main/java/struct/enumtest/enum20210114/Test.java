package struct.enumtest.enum20210114;

enum Color {
    RED, GREEN, BLUE
}

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Test.java
 * @createTime 2021-01-14 14:14:00
 */
public class Test {
    public static void main(String[] args) {
        Color1[] arr = Color1.values();
        for (Color1 col : arr) {
            System.out.println(col + " at index " + col.ordinal());
        }
        System.out.println(Color1.valueOf("RED"));
    }
}

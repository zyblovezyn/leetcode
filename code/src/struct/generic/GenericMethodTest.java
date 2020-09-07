package struct.generic;

public class GenericMethodTest {
    public static <E> void printArray(E[] arrs) {
        for (E element : arrs) {
            System.out.printf("%s  ", element);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
        printArray(intArray);
        printArray(doubleArray);
        printArray(charArray);

    }
}

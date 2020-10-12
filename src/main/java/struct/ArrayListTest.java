package struct;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.iterator().forEachRemaining(x-> System.out.println(x));
    }
}

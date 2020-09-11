package struct.generic;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List arrayList = new ArrayList();
        arrayList.add("zhangsn");
        arrayList.add(100);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }


        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        Class classStringList = stringList.getClass();
        Class classIntergerList=integerList.getClass();

        if (classIntergerList.equals(classStringList)) {
            System.out.println(true);
        }
    }
}

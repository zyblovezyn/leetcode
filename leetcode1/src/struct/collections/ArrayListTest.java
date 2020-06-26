package struct.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(i);
        }

        //迭代器遍历
        Iterator<Integer> it= list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        //索引值循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        //for循环遍历
        for (Integer number:list){
            System.out.println(number);
        }

        Integer[] integer=list.toArray(new Integer[0]);
    }
}

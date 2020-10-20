package utils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static void print(Object... args) {
        StringBuilder stringBuilder=new StringBuilder();
        for (Object arg : args) {
            stringBuilder.append(arg).append("  ");
        }
        System.out.println(stringBuilder.toString());
    }

    public static <E> List<List<E>> getElementsBySplitCount(List<E> list,int count) {
        int size = count;
        List<E> listTemp = new ArrayList<>();
        List<List<E>> listRet = new ArrayList<>();
        for (int i = 0; i < list.size(); i++)
        {
            listTemp.add(list.get(i));
            if (i % size == 0 && i != 0)
            {
                List<E> temp =new ArrayList<>(listTemp);
                listRet.add(temp);
                listTemp.clear();
            }
        }
        if (listTemp.size() != 0)
        {
            listRet.add(listTemp);
        }
        return listRet;
    }
}

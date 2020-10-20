package utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CommonUtils {

    /**
     * @Description: 打印函数
     * <p>
     * @Param: [args]
     * @return: void
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static void print(Object... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object arg : args) {
            stringBuilder.append(arg).append("  ");
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * @Description: 获取分割后的数组集合
     * <p>
     * @Param: [list]
     * @return: java.util.List<java.util.List < E>>
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static <E> List<List<E>> getElementsBySplitCount(List<E> list) {
        return getElementsBySplitCount(list, 500);
    }

    /**
     * @Description: 获取分割后的数组集合
     * <p>
     * @Param: [list, count]
     * @return: java.util.List<java.util.List < E>>
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static <E> List<List<E>> getElementsBySplitCount(List<E> list, int count) {
        int size = count;
        List<E> listTemp = new ArrayList<>();
        List<List<E>> listRet = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listTemp.add(list.get(i));
            if (i % size == 0 && i != 0) {
                List<E> temp = new ArrayList<>(listTemp);
                listRet.add(temp);
                listTemp.clear();
            }
        }
        if (listTemp.size() != 0) {
            listRet.add(listTemp);
        }
        return listRet;
    }

    /**
     * @Description: 一种类型的值赋给另一个类型
     * <p>
     * @Param: [resourceType, returnType]
     * @return: R
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static <S extends Comparable<S>, R extends Comparable<R>> R type2Type(S resourceType, R returnType) throws IllegalAccessException {
        if (resourceType == null || returnType == null) {
            throw new NullPointerException();
        }
        List<Field> resFields = Arrays.asList(resourceType.getClass().getDeclaredFields());
        List<Field> retFields = Arrays.asList(returnType.getClass().getDeclaredFields());
        Collections.sort(resFields, (x, y) -> x.getName().compareTo(y.getName()));
        Collections.sort(retFields, (x, y) -> x.getName().compareTo(y.getName()));
        for (Field retField : retFields) {
            for (Field resField : resFields) {
                if (resField.getName().equals(retField.getName())) {
                    if (resField.getType().equals(retField.getType())) {
                        retField.setAccessible(true);
                        resField.setAccessible(true);
                        retField.set(returnType, resField.get(resourceType));
                    }
                }
            }
        }
        return returnType;
    }
}

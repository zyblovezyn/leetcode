package utils;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: 工具类
 * <p>
 * @Author: Mr.Miles
 * @Date: 2020/10/22
 */
public final class CommonUtils {

    /**
     * @Description: 打印函数
     * <p>
     * @Param: [args]
     * @return: void
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static void print(@NotNull Object... args) {
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
    public static <E> List<List<E>> getElementsBySplitCount(@NotNull List<E> list) {
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
    public static <E> List<List<E>> getElementsBySplitCount(@NotNull List<E> list, int count) {
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
    public static <S, R> R type2Type(@NotNull S resourceType,@NotNull R returnType) throws IllegalAccessException {
        Field[] resFields = resourceType.getClass().getDeclaredFields();
        Field[] retFields = returnType.getClass().getDeclaredFields();
        Arrays.sort(resFields, Comparator.comparing(Field::getName));
        for (Field retField : retFields) {
            int index = Arrays.binarySearch(resFields, retField, Comparator.comparing(Field::getName));
            if (index < 0) {
                continue;
            }
            Field resField = resFields[index];
            boolean equalType = resField.getType().equals(retField.getType()) ||
                    retField.getType().getName().equals(Object.class.getTypeName());
            if (equalType) {
                retField.setAccessible(true);
                resField.setAccessible(true);
                retField.set(returnType, resField.get(resourceType));
            }
        }
        return returnType;
    }

    /**
     * @Description: 一种类型的值赋给另一个类型
     * <p>
     * @Param: [resourceType, returnType]
     * @return: R
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static <S, R> R type2TypeExtension(@NotNull S resourceType,@NotNull R returnType) throws IllegalAccessException {
        Field[] resFields = resourceType.getClass().getDeclaredFields();
        Field[] retFields = returnType.getClass().getDeclaredFields();
        Arrays.sort(resFields, Comparator.comparing(Field::getName));
        for (Field retField : retFields) {
            int index = Arrays.binarySearch(resFields, retField, Comparator.comparing(Field::getName));
            if (index < 0) {
                continue;
            }
            Field resField = resFields[index];

            boolean equalType = resField.getType().equals(retField.getType()) ||
                    retField.getType().getName().equals(Object.class.getTypeName());
            retField.setAccessible(true);
            resField.setAccessible(true);
            if (equalType) {
                retField.set(returnType, resField.get(resourceType));
                continue;
            } else {
                String retType = retField.getType().getName();
                String resType = resField.getType().getName();

                //ret int 类型
                boolean canChange = false;
                Object value;
                switch (retType) {
                    case "int":
                    case "Integer":
                        if (resType.equals(int.class.getTypeName()) ||
                                resType.equals(Integer.class.getTypeName()) ||
                                resType.equals(long.class.getTypeName()) ||
                                resType.equals(Long.class.getTypeName()) ||
                                resType.equals(double.class.getTypeName()) ||
                                resType.equals(Double.class.getTypeName()) ||
                                resType.equals(short.class.getTypeName()) ||
                                resType.equals(Short.class.getTypeName()) ||
                                resType.equals(Number.class.getTypeName())) {

                        }
                        break;
                    default:
                        break;
                }
                if (canChange) {
                    retField.set(returnType, resField.get(resourceType));
                }
            }
        }
        return returnType;
    }
}

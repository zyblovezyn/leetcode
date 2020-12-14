package utils;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.*;

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
     * @Description: 打印函数
     * <p>
     * @Param: [args]
     * @return: void
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static void println(@NotNull Object... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object arg : args) {
            stringBuilder.append(arg).append(StringUtils.line());
        }
        System.out.println(stringBuilder.toString());
    }
    public static void printInt(@NotNull int[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int arg : args) {
            stringBuilder.append(arg).append(StringUtils.line());
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
     * todo
     */
    public static <S, R> R type2TypeExtension(@NotNull S resourceType, @NotNull R returnType) throws IllegalAccessException {
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

    /**
     * @param from
     * @Description: 一种类型的值赋给另一个类型
     * <p>
     * @Param: [resourceType, returnType]
     * @return: R
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static <S, R> List<R> list2List(@NotNull List<S> from, @NotNull R to) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        List<R> tos = new ArrayList<>();
        for (Iterator<S> iterator = from.iterator(); iterator.hasNext(); ) {
            S res = iterator.next();
            R r = (R) to.getClass().newInstance();
            type2Type(res, r);
            tos.add(r);
        }
        return tos;
    }

    /**
     * @Description: 一种类型的值赋给另一个类型
     * <p>
     * @Param: [resourceType, returnType]
     * @return: R
     * @Author: Mr.Miles
     * @Date: 2020/10/20
     */
    public static <S, R> R type2Type(@NotNull S from, @NotNull R to) throws IllegalAccessException {
        Field[] fromFields = from.getClass().getDeclaredFields();
        Field[] toFields = to.getClass().getDeclaredFields();
        Arrays.sort(fromFields, Comparator.comparing(Field::getName));
        for (Field retField : toFields) {
            int index = Arrays.binarySearch(fromFields, retField, Comparator.comparing(Field::getName));
            if (index < 0) {
                continue;
            }
            Field resField = fromFields[index];
            boolean equalType = resField.getType().equals(retField.getType()) ||
                    retField.getType().getName().equals(Object.class.getTypeName());
            if (equalType) {
                retField.setAccessible(true);
                resField.setAccessible(true);
                retField.set(to, resField.get(from));
            }
        }
        return to;
    }

    public static boolean isAllEquals(@NotNull Object... args) {
        for (Object arg1 : args) {
            for (Object arg2 : args) {
                if (!arg1.equals(arg2)) {
                    return false;
                }
            }
        }
        return true;
    }
}

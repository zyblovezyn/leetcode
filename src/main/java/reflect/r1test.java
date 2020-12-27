package reflect;

import reflect.entry.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName r1test.java
 * @createTime 2020-11-28 19:27:00
 */
public class r1test {
    public static void main(String[] args) throws ClassNotFoundException {
        Person p1 = new Person();
        Class c1=p1.getClass();

        Class c2=Person.class;

        Class c3=Class.forName("reflect.entry.Person");

        /*System.out.println(CommonUtils.isAllEquals(c1,c2,c3));
        CommonUtils.print(c1.getName());
        CommonUtils.print(c1.getFields());
        System.out.println("-------------------------");
        CommonUtils.println(c1.getDeclaredFields());
        CommonUtils.println(12,12);*/

        Field[] allFields = c1.getDeclaredFields();
        for (Field field : allFields) {
            System.out.println(field.getName());
        }

        Method[] methods = c2.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println();

        Method[] allMethods = c2.getDeclaredMethods();
        for (Method allMethod : allMethods) {
            System.out.println(allMethod.getName());
        }
    }
}

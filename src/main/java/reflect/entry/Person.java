package reflect.entry;/**
 * @Description TODO
 *<p>
 * @ClassName Person.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2020-11-28 19:28:00
 */
public class Person {
    //私有属性
    private String name = "Tom";
    //公有属性
    public int age = 18;
    //?造方法
    public Person() {
    }
    //私有方法
    private void say(){
        System.out.println("private say()...");
    }
    //公有方法
    public void work(){
        System.out.println("public work()...");
    }
}

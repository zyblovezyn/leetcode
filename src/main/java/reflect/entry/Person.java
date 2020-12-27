package reflect.entry;

/**
 * @Description TODO
 *<p>
 * @ClassName Person.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2020-11-28 19:28:00
 */
public class Person {
    //���L����
    private String name = "Tom";
    //���L����
    public int age = 18;
    //?�����@
    public Person() {
    }
    //���L���@
    private void say(){
        System.out.println("private say()...");
    }
    //���L���@
    public void work(){
        System.out.println("public work()...");
    }
}

package designzen.prototype.p1;

import java.util.ArrayList;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Thing.java
 * @createTime 2021-01-15 15:35:00
 */
public class Thing implements Cloneable {
    private Integer age;
    private ArrayList<Integer> arrayList=new ArrayList<>();

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    Thing() {
        System.out.println("构造函数被执行了。。。。");
    }

    @Override
    protected Thing clone() throws CloneNotSupportedException {
        Thing thing = null;
        try {
            thing = (Thing) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thing;
    }
}

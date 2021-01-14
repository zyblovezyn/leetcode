package struct.enumtest.enum20210114;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Color.java
 * @createTime 2021-01-14 14:20:00
 */
public enum  Color1 {
    RED,GREEN,BLUE;

    Color1() {
        System.out.println("Constructor called for " + this.toString());
    }

    public void colorInfo() {
        System.out.println("Universal Color");
    }
}



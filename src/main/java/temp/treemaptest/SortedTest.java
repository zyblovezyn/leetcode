package temp.treemaptest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Miles
 * @version 1.0.0
 * @ClassName SortedTest.java
 * @Description
 * @createTime 2020-10-22 18:52:00
 */
@Data
public class SortedTest {
    private int age;

    public SortedTest(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

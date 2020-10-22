package temp.treemaptest;

import java.util.Comparator;

/**
 * @author Miles
 * @version 1.0.0
 * @ClassName SortedTestComparator.java
 * @Description TODO
 * @createTime 2020-10-22 18:53:00
 */
public class SortedTestComparator implements Comparator<SortedTest> {
    @Override
    public int compare(SortedTest o1, SortedTest o2) {
        return o1.getAge() - o2.getAge();

    }
}

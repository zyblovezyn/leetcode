package temp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class DateTimeTest {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String input = "2019-11-24";
        Date date;
        try {
            date = simpleDateFormat.parse(input);
            System.out.println(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashSet<Integer> sets = new HashSet<>();
        LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>();
        for (int i = 0; i < 1000; i++) {
            sets.add(i);
            linkedHashSet.add(i);
        }
        sets.add(1);
        sets.add(2);
        linkedHashSet.add(1);
        linkedHashSet.add(2);
        sets.forEach(System.out::println);
        System.out.println();
        linkedHashSet.forEach(System.out::println);
    }
}

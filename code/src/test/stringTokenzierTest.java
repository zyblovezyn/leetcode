package test;

import java.time.Instant;
import java.util.StringTokenizer;

public class stringTokenzierTest {
    public static void main(String[] args) {
        String str = "runoob,google,taobao,facebook,zhihu";
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }

        Instant instant = Instant.now();
        System.out.println(instant.toEpochMilli());

    }
}

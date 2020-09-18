package temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest01 {
    public static void main(String[] args) {
        System.out.println("-------------------------");
        test01();
        System.out.println("-------------------------");
        test02();
        System.out.println("-------------------------");
        test03();
        System.out.println("-------------------------");
        test04();
    }

    public static void test01() {
        String content = "I am noob from runoob.com";
        String pattern = ".*runoob";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
    }

    public static void test02() {
        String line = "This order was plaved for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        if (m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
        }
    }

    public static void test03() {
        Pattern pattern = Pattern.compile("\\bcat\\b");
        String input = "cat cat cat cattie cat";
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println(count);
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        }
    }

    public static void test04() {
        String regex = "foo";
        String input = "fooooooooooooooooo";
        String input2 = "ooooofoooooooooooo";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(input);
        Matcher matcher2=pattern.matcher(input2);

        System.out.println("Curren Regex is :" + regex);
        System.out.println("input = " + input);
        System.out.println("input2 = " + input2);
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        System.out.println("lookingAt(): " + matcher.lookingAt());
        System.out.println("matches(): " + matcher.matches());
        System.out.println("lookingAt(): " + matcher2.lookingAt());
    }
}

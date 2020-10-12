package temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexTest {
    public static void main(String[] args) {
        String content = "I am noob " + "from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);

        regexPattern();
        System.out.println("-------------");
        startTest();
        System.out.println("-------------");
        regexMatches();
        System.out.println("-------------");
        replaceTest();
    }

    public static void regexPattern() {
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    public static void startTest() {
        String regex = "\\bcat\\b";
        String input = "cat cat cat cattie cat";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("===========");
            System.out.println(count);
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        }
    }

    public static void regexMatches() {
        String regex = "foo";
        String input = "fooooooooooooooooo";
        String input2 = "ooooofoooooooooooo";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        Matcher matcher2 = pattern.matcher(input2);

        System.out.println("lookingAt(): " + matcher.lookingAt());
        System.out.println("matches(): " + matcher.matches());
        System.out.println("looking(): " + matcher2.lookingAt());
    }

    public static void replaceTest() {
        String regex = "dog";
        String input = "The dog says meow." + "All dogs say meow.";
        String replace = "cat";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        input = m.replaceAll(replace);
        System.out.println(input);
    }

    public static void appendTest() {
        String REGEX = "a*b";
        String INPUT = "aabfooaabfooabfoobkkk";
        String REPLACE = "-";

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, REPLACE);
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

}

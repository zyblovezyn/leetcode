package struct.generic.genericinterface;

import java.util.Random;

public class ManGenerator implements Generator<String> {
    String[] names = {"zhangsan", "lisi", "wangwu"};

    @Override
    public String text() {
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    public static void main(String[] args) {
        ManGenerator manGenerator=new ManGenerator();
        System.out.println(manGenerator.text());
    }
}

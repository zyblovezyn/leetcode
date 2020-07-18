package struct.generic.genericinterface;

import java.util.Random;

public class NumberGenerator implements Generator<Integer> {
    int[] ages = {17, 18, 19};
    @Override
    public Integer text() {
        Random random = new Random();
        return ages[random.nextInt(3)];
    }

    public static void main(String[] args) {
        NumberGenerator age=new NumberGenerator();
        System.out.println(age.text());
    }
}

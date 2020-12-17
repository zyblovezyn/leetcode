package struct.collections;

import utils.CommonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description TODO
 *<p>
 * @ClassName LambdaClass.java
 * @author Miles
 * @version 1.0.0
 * @createTime 2020-12-14 19:41:00
 */
public class LambdaClass {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7);
        evaluate(list,n->true);

        CommonUtils.printLine();
        evaluate(list,n->false);
        CommonUtils.printLine();

        evaluate(list, n -> n % 2 == 0);
        CommonUtils.printLine();

        evaluate(list, n -> n % 2 == 1);
        CommonUtils.printLine();

        evaluate(list, n -> n > 5);
        CommonUtils.printLine();

        list.stream().map(x->x*x).forEach(System.out::println);

        list.stream().map(x->x*x).reduce((x,y)->x+y).get();
    }

    public  static  void evaluate(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer : list) {
            if(predicate.test(integer)){
                System.out.print(integer+ " ");
            }
        }
    }
}

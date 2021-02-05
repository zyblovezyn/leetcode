package temp.responsechain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName ChainOne.java
 * @createTime 2021-01-20 15:18:00
 */
public class ChainOne extends AbstractChain {


    @Override
    public void echo(Request request) {
        System.out.println("ChainOne.echo");
    }
}

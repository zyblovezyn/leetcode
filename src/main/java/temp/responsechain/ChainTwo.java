package temp.responsechain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName ChainTwo.java
 * @createTime 2021-01-20 15:19:00
 */
public class ChainTwo extends AbstractChain {
    @Override
    public void echo(Request request) {
        System.out.println("ChainTwo.echo");
    }
}

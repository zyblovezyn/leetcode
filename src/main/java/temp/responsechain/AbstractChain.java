package temp.responsechain;

import lombok.Data;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName AbstractChain.java
 * @createTime 2021-01-20 15:15:00
 */
@Data
public abstract class AbstractChain {

    private Level level;

    private AbstractChain nextChain;

    protected void handler(Request request) {
        if (request.getLevel() == this.getLevel()) {
            echo(request);
        } else {
            if (this.getNextChain() != null) {
                this.getNextChain().echo(request);
            } else {
                //
                System.out.println("no chain!");
            }
        }
    }

    public abstract void echo(Request request);
}

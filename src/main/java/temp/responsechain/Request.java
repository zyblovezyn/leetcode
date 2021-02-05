package temp.responsechain;

import lombok.Data;
import lombok.Getter;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName request.java
 * @createTime 2021-01-20 15:10:00
 */

public class Request {
    @Getter
    private Level level;
    @Getter
    private String data;
}

package utils;

import java.io.Serializable;

/**
 * @author Miles
 * @version 1.0.0
 * @ClassName StringUtils.java
 * @Description TODO
 * @createTime 2020-10-22 14:17:00
 */
public class StringUtils implements Serializable {
    public final static char[] lowerChars = {
            'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x',
            'y', 'z'
    };
    public final static char[] upperChars = {
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };
    private static final long serialVersionUID = -1553648220029603605L;

    public StringUtils() {
    }
}

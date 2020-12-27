package utils;

import java.io.Serializable;

/**
 * @author Miles
 * @version 1.0.0
 * @Description 字符串工具类
 * @ClassName StringUtils.java
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

    public static String line() {
        return System.getProperties().getProperty("line.separator");
    }


    /**
     * @Description: <p>
     * @Param: [str, start, end]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/22
     */
    public static synchronized String subString(String str, int start, int end) {
        char[] arr = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = start; i < arr.length; i++) {
            stringBuffer.append(arr[i]);
            if (i == end) {
                break;
            }
        }
        return stringBuffer.toString();
    }
}

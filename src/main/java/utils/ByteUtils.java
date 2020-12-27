package utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

/**
 * @Description: <p>
 * @Author: Mr.Miles
 * @Date: 2020/10/22
 */
public class ByteUtils implements Serializable {
    private static final long serialVersionUID = -7395625324145448823L;

    public ByteUtils() {
    }

    /**
     * @Description: 二进制流转Base64流
     * <p>
     * @Param: [data]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/17
     */
    public static String getBlob2Base64(byte[] data) throws IOException {
        return data != null ? Base64.getEncoder().encode(data).toString() : "";
    }

    /**
     * @Description: Base64流转二进制流
     * <p>
     * @Param: [base64String]
     * @return: byte[]
     * @Author: Mr.Miles
     * @Date: 2020/10/17
     */
    public static byte[] getBase64ToBlob(String base64String) throws IOException {
        return base64String != null ? Base64.getDecoder().decode(base64String) : null;
    }
}

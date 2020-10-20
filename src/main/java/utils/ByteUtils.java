package utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class ByteUtils {

    /**
     * @Description: 二进制流转Base64流
     * <p>
     * @Param: [data]
     * @return: java.lang.String
     * @Author: Mr.Miles
     * @Date: 2020/10/17
     */
    public static String getBlob2Base64(byte[] data) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
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
        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        return base64String != null ? decoder.decodeBuffer(base64String) : null;
    }
}

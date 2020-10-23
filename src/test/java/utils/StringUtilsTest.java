package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void subString() {

        String str = "123456789";
        String ret = StringUtils.subString(str, 1, 2);
        Assertions.assertEquals("23", ret);

        ret = StringUtils.subString(str, 1, 3);
        Assertions.assertEquals("234", ret);

        ret = StringUtils.subString(str, 1, 4);
        Assertions.assertEquals("2345", ret);

        ret = StringUtils.subString(str, 4, 15);
        Assertions.assertEquals("56789", ret);
    }
}
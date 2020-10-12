package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class leetcode1108 {
    public String defangIPaddr(String address) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : address.toCharArray()) {
            if (c != '.')
                stringBuilder.append(c);
            else
                stringBuilder.append("[").append(c).append("]");
        }
        return stringBuilder.toString();
    }


}

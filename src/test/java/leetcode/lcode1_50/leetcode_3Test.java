package leetcode.lcode1_50;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class leetcode_3Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void lengthOfLongestSubstring() {
        leetcode_3 leetcode_3=new leetcode_3();
        int count=  leetcode_3.lengthOfLongestSubstring("abcabcbb");
        Assertions.assertEquals(3,count);

        count=  leetcode_3.lengthOfLongestSubstring("bbbbb");
        Assertions.assertEquals(1,count);

        count=  leetcode_3.lengthOfLongestSubstring("pwwkew");
        Assertions.assertEquals(3,count);

        count=  leetcode_3.lengthOfLongestSubstring(" ");
        Assertions.assertEquals(1,count);
    }
}
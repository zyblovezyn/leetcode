package leetcode;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Leetcode1512Test {

    @Test
    void numIdenticalPairs() {
        Leetcode1512 leetcode1512 = new Leetcode1512();
        int[] arr1 = new int[6];
        arr1[0] = 1;
        arr1[1] = 2;
        arr1[2] = 3;
        arr1[3] = 1;
        arr1[4] = 1;
        arr1[5] = 3;
        Assert.assertEquals(4,leetcode1512.numIdenticalPairs(arr1));

        int[] arr2 = new int[4];
        arr2[0]=1;
        arr2[1]=1;
        arr2[2]=1;
        arr2[3]=1;
        Assert.assertEquals(6,leetcode1512.numIdenticalPairs(arr2));

    }
}
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leetcode1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> resList = new ArrayList<>();
        int max = candies[0];
        for (int i = 0; i < candies.length; i++) {
            if (max < candies[i]){
                max=candies[i];
            }
        }
        for (int i = 0; i<candies.length; i++){
            resList.add(max<=candies[i]+extraCandies);
        }
        return resList;
    }
}

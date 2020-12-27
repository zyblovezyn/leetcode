package mianshiti;

public class m46 {
    public static void main(String[] args) {
        System.out.println(translateNum(18580));
        System.out.println(translateNum(11));
        System.out.println(translateNum(506));
        System.out.println(translateNum(18822));


    }
    public static int translateNum(int num) {
        char[] nums=Integer.toString(num).toCharArray();
        int resInt=1;
        for (int i=0;i<nums.length-1;i++){
            String value=Character.toString(nums[i])+Character.toString(nums[i+1]);
            if(value.startsWith("0")){
                continue;
            }
            int intValue=Integer.parseInt(value);
            if(intValue<26)
                resInt++;
        }
        return resInt;
    }
}

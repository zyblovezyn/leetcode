package temp;

import utils.CommonUtils;

import java.util.Vector;

public class Test02 {
    public static void main(String[] args) {
//        int a=1;
//        System.out.println(a>>1);
//        a=2;
//        System.out.println(a>>1);
//        a=10;
//        System.out.println(a>>1);

        Integer[] arr=new Integer[5];
        CommonUtils.fillArray(arr,1,1);

        int removeIndex=2;
        int length=arr.length-removeIndex-1;
        System.arraycopy(arr,removeIndex+1,arr,removeIndex,length);
        arr[arr.length-1]=0;
        CommonUtils.println(arr);

        Vector<Integer> vector = new Vector<>();
        vector.add(1);
    }
}

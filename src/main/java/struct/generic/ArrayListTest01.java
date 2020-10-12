package struct.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ArrayListTest01 {
    public static void main(String[] args) {
        ArrayList<String> sites = new ArrayList<>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");

        for (int i = 0; i < sites.size(); i++) {
            System.out.println(sites.get(i));
        }
        System.out.println("-------------------------");
        for (String str : sites) {
            System.out.println(str);
        }
        System.out.println("-------------------------");
        test01();
        System.out.println("-------------------------");
        LinkedListTest();
    }
    public static void test01() {
        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        myNumbers.add(33);
        myNumbers.add(15);
        myNumbers.add(20);
        myNumbers.add(34);
        myNumbers.add(8);
        myNumbers.add(12);
        Collections.sort(myNumbers);
        for (int i : myNumbers) {
            System.out.println(i);
        }
    }

    public static void LinkedListTest() {
        LinkedList<String> sites = new LinkedList<>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        sites.addLast("Wiki");

        for (int i = 0; i < sites.size(); i++) {
            System.out.println(sites.get(i));
        }

        for (String item : sites) {
            System.out.println(item);
        }
    }
}

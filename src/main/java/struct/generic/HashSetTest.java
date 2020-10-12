package struct.generic;

import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> sites = new HashSet<>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Zhihu");
        sites.add("Runoob");   // 重复的元素不会被添加
        sites.remove("Runoob");
        for (String item : sites) {
            System.out.println(item);
        }
    }
}

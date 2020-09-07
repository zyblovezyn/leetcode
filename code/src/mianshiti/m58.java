package mianshiti;

public class m58 {
    public static void main(String[] args) {
        reverseLeftWords("abcdefg",2);
    }
    public static String reverseLeftWords(String s, int n) {
        char[] characters=new char[s.length()];
        char[] chars=s.toCharArray();
        for (int i=0;i<n;i++){
            characters[i+s.length()-n]=chars[i];
        }
        for (int i=n;i<s.length();i++){
            characters[i-n]=chars[i];
        }
        System.out.println(String.valueOf(characters));
        return String.valueOf(characters);
    }
}

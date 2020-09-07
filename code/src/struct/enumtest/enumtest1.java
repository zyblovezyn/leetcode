package struct.enumtest;

enum Signaltest1 {
    GREEN, RED, YELLOW
}

public class enumtest1 {
    public static void main(String[] args) {
        for (int i = 0; i < Signaltest1.values().length; i++) {
            System.out.println("–‡?¬?F " + Signaltest1.values()[i]);
        }

        compare(Signaltest1.valueOf("RED"));

        for (int i = 0; i < Signaltest1.values().length; i++) {
            System.out.println(Signaltest1.values()[i].ordinal());
        }



    }

    public static void compare(Signaltest1 signaltest1) {
        for (int i = 0; i < Signaltest1.values().length; i++) {
            System.out.println(signaltest1 + "—^" + Signaltest1.values()[i] + "“I”ä??‰ÊF" + signaltest1.compareTo(Signaltest1.values()[i]));
        }
    }
}

package struct.enumtest;

public enum ColorTest {
    RED("红色", 1),
    GREEN("绿色", 2),
    BLANK("白色", 3),
    YELLO("黄色", 4);

    private final int index;
    private final String name;

    ColorTest(String name, int index) {
        this.name = name;
        this.index = index;
    }


    // 普通方法  
    public static String getName(int index) {
        for (ColorTest c : ColorTest.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}

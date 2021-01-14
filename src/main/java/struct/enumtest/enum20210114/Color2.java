package struct.enumtest.enum20210114;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Color2.java
 * @createTime 2021-01-14 14:26:00
 */
public enum Color2 {
    RED {
        @Override
        public String getColor2() {
            return "红色";
        }
    }, GREEN {
        @Override
        public String getColor2() {
            return "绿色";
        }
    }, BLUE {
        @Override
        public String getColor2() {
            return "蓝色";
        }
    };

    public abstract String getColor2();
}

class Test1431 {
    public static void main(String[] args) {
        for (Color2 col : Color2.values()) {
            System.out.print(col.getColor2() + ",");
        }
    }
}

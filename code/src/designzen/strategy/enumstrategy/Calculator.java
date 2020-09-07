package designzen.strategy.enumstrategy;

public enum Calculator {
    ADD("+"){
        public int exec(int a, int b) {
            return a+b;
        }
    },
    SUB("-"){
        public int exec(int a, int b) {
            return a - b;
        }
    };
    String value="";

    Calculator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract int exec(int a, int b);
}

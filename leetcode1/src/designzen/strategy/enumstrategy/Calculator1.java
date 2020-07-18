package designzen.strategy.enumstrategy;

public enum  Calculator1 {
    ADD(){
        @Override
        public int exec(int a, int b) {
            return a+b;
        }
    },
    SUB(){
        @Override
        public int exec(int a, int b) {
            return a-b;
        }
    };

    private String value;

    Calculator1(String value) {
        this.value = value;
    }

    Calculator1() {
    }

    public abstract int exec(int a, int b);
}

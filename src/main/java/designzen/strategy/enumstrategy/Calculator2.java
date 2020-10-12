package designzen.strategy.enumstrategy;

public enum Calculator2 {
    ADD() {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },
    SUB() {
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    };

    Calculator2() {
    }

    public abstract int exec(int a, int b);
}

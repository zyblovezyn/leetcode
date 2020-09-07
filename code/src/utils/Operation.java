package utils;

public enum Operation {

    PLUS {
        @Override
        public double calculate(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double calculate(double x, double y) {
            return x - y;
        }
    },
    TIMES{
        @Override
        public double calculate(double x, double y) {
            return x*y;
        }
    },
    DIVID{
        @Override
        public double calculate(double x, double y) {
            return x/y;
        }
    };

    public abstract double calculate(double x, double y);
    }

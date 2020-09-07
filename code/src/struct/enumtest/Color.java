package struct.enumtest;

public enum  Color {
    RED,
    GREEN,
    BLANK,
    YELLOW
}

enum Signal {
    GREEN,YELLOW,RED
}

class TrafficLight {
    Signal color = Signal.RED;

    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.RED;
                break;
            case YELLOW:
                color = Signal.YELLOW;
                break;
            default:
                break;
        }
    }
}

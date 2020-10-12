package designzen.flyweight.test4;

public abstract class Flyweight {
    private String inside;

    private String outSide;

    public Flyweight(String outSide) {
        this.outSide = outSide;
    }

    public abstract void operate();

    public String getInside() {
        return inside;
    }

    public void setInside(String inside) {
        this.inside = inside;
    }
}

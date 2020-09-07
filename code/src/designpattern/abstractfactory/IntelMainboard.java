package designpattern.abstractfactory;

public class IntelMainboard implements Mainborad {

    private int cpuHoles = 0;

    public IntelMainboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("Intel主板的CPU插槽孔数是：" + cpuHoles);
    }
}

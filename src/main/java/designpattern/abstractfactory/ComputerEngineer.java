package designpattern.abstractfactory;

public class ComputerEngineer {

    private Cpu cpu=null;

    private Mainborad mainborad=null;

    public void makeComputer(int cpuType,int mainboardType){
        prepareHardwares(cpuType,mainboardType);
    }
    private void prepareHardwares(int cpuType,int mainboard){
        this.cpu= CpuFactory.createCpu(cpuType);
        this.mainborad=MainboardFactory.createMainboard(mainboard);

        this.cpu.calculate();
        this.mainborad.installCPU();
    }
}

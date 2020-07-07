package designzen.visitor.test2;

public class CommonEmployee extends Employee {

    private String job;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    protected void acccept(IVisitor visitor) {
        visitor.visit(this);
    }
}

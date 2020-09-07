package designzen.visitor.test2;

public abstract class Employee {
    public static final int MALE = 0;//0 代表男性
    public static final int FEMALE = 1;//1 代表女性
    private String name;
    private int salary;
    private int sex;

    public static int getMALE() {
        return MALE;
    }

    public static int getFEMALE() {
        return FEMALE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    protected abstract void acccept(IVisitor visitor);
}

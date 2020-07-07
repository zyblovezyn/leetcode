package designzen.visitor.test1;

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

    public final void report() {
        StringBuffer sb = new StringBuffer();
        sb.append("姓名").append(" ").append(this.name).append("\n");
        sb.append("性别").append(this.sex == FEMALE ? "女" : "男").append("\n");
        sb.append("薪水：").append(this.salary).append("\n");
        System.out.println(sb);
    }

    protected abstract String getOtherInfo();
}

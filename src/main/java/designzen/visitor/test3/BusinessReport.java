package designzen.visitor.test3;

import java.util.LinkedList;
import java.util.List;

public class BusinessReport {
    public List<Staff> staffList = new LinkedList<>();

    public BusinessReport() {
        staffList.add(new Manager("经理-A"));
        staffList.add(new Engineer("工程师-A"));
        staffList.add(new Engineer("工程师-B"));
        staffList.add(new Engineer("工程师-C"));
        staffList.add(new Manager("经理-B"));
        staffList.add(new Engineer("工程师-D"));
    }

    public void showReport(Visitor visitor) {
        for (Staff staff : this.staffList) {
            staff.accept(visitor);
        }
    }
}

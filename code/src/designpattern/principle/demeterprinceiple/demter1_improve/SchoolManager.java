package designpattern.principle.demeterprinceiple.demter1_improve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SchoolManager {
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Employee employee = new Employee();
            employee.setId("学校内部员工id=" + i);
            list.add(employee);
        }
        return list;
    }
    void printAllEmployee(CollegeManager sub){
        sub.printCollegeEmployee();
        List<Employee> list2=this.getAllEmployee();
        System.out.println("----------学校总部员工！");
        Iterator<Employee> it2=list2.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next().getId());
        }
    }
}

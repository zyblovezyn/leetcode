package designpattern.principle.demeterprinceiple.demeter1;

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

        List<CollegeEmployee> list1=sub.getAllEmployee();
        System.out.println("分公司员工！");
        Iterator<CollegeEmployee> it1=list1.iterator();
        while (it1.hasNext()){
            System.out.println(it1.next().getId());
        }

        List<Employee> list2=this.getAllEmployee();
        System.out.println("----------学校总部员工！");
        Iterator<Employee> it2=list2.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next().getId());
        }
    }
}
